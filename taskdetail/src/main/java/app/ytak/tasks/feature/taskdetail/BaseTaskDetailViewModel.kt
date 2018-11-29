package app.ytak.tasks.feature.taskdetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.data.util.ext.filterOptionalNotNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

open class BaseTaskDetailViewModel constructor(
    protected val taskAction: TaskAction,
    protected val taskStore: TaskStore
) : LifecycleObserver {

    val currentTask by lazy { taskStore.currentTask().filterOptionalNotNull() }

    private val createdDisposables = CompositeDisposable()

    fun updateDoneStatus(taskId: String, isDone: Boolean) {
        taskAction.updateDoneStatus(taskId, isDone)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        currentTask
            .distinctUntilChanged { task -> task.id }
            .switchMap { taskStore.task(it.id).filterOptionalNotNull().distinctUntilChanged().skip(1L) }
            .subscribe { taskAction.updateCurrentTask(it) }
            .addTo(createdDisposables)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        createdDisposables.clear()
    }
}
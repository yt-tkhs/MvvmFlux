package app.ytak.tasks.feature.taskdetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.data.util.ext.filterOptionalNotNull
import app.ytak.tasks.base.di.scope.PerFragmentScope
import javax.inject.Inject

@PerFragmentScope
class TaskDetailViewModel @Inject constructor(
    private val taskAction: TaskAction,
    private val taskStore: TaskStore
) : LifecycleObserver {

    private lateinit var taskId: String

    val task by lazy { taskStore.task(taskId).filterOptionalNotNull() }

    fun initialize(taskId: String) {
        this.taskId = taskId
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        taskAction.refreshTask(taskId)
    }

    fun updateDoneStatus(taskId: String, isDone: Boolean) {
        taskAction.updateDoneStatus(taskId, isDone)
    }
}
package app.ytak.tasks.feature.tasklist

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.di.scope.PerFragmentScope
import app.ytak.tasks.feature.taskdetail.TaskLoader
import javax.inject.Inject

@PerFragmentScope
class TaskListViewModel @Inject constructor(
    override val taskAction: TaskAction,
    private val taskStore: TaskStore
) : TaskLoader(), LifecycleObserver {

    val tasks by lazy { taskStore.tasks() }

    val errors by lazy { taskStore.errors() }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        taskAction.refreshTasks()
    }

    fun updateDoneStatus(taskId: String, isDone: Boolean) {
        taskAction.updateDoneStatus(taskId, isDone)
    }
}
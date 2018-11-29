package app.ytak.tasks.feature.tasklist

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.di.scope.PerFragmentScope
import app.ytak.tasks.base.util.JobContainer
import app.ytak.tasks.base.util.ext.addTo
import app.ytak.tasks.feature.taskdetail.TaskLoader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@PerFragmentScope
class TaskListViewModel @Inject constructor(
    override val taskAction: TaskAction,
    private val taskStore: TaskStore
) : TaskLoader, LifecycleObserver {

    val tasks by lazy { taskStore.tasks() }

    val errors by lazy { taskStore.errors() }

    private var jobContainer = JobContainer()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        taskAction.refreshTasks().addTo(jobContainer)
        GlobalScope.launch {
            Timber.d("start")
            delay(10000000)
            Timber.d("end")
        }.addTo(jobContainer)
    }

    fun updateDoneStatus(taskId: String, isDone: Boolean) {
        taskAction.updateDoneStatus(taskId, isDone)
    }
}
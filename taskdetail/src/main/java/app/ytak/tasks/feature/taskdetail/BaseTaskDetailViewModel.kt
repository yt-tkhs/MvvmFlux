package app.ytak.tasks.feature.taskdetail

import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.data.util.ext.filterOptionalNotNull

open class BaseTaskDetailViewModel constructor(
    protected val taskAction: TaskAction,
    protected val taskStore: TaskStore
) {

    val currentTask by lazy { taskStore.currentTask().filterOptionalNotNull() }

    fun updateDoneStatus(taskId: String, isDone: Boolean) {
        taskAction.updateDoneStatus(taskId, isDone)
    }
}
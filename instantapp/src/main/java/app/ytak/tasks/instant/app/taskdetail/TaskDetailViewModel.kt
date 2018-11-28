package app.ytak.tasks.instant.app.taskdetail

import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.feature.taskdetail.BaseTaskDetailViewModel
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(
    taskAction: TaskAction,
    taskStore: TaskStore
) : BaseTaskDetailViewModel(taskAction, taskStore) {

    fun refreshTask(taskId: String) {
        taskAction.loadTask(taskId)
    }
}
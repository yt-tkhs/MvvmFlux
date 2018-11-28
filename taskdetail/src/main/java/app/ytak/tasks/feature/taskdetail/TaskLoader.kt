package app.ytak.tasks.feature.taskdetail

import app.ytak.tasks.base.data.flux.action.TaskAction

abstract class TaskLoader {

    protected abstract val taskAction: TaskAction

    fun loadTask(taskId: String) {
        taskAction.loadTask(taskId)
    }
}
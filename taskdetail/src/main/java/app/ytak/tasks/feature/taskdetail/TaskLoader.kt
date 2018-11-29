package app.ytak.tasks.feature.taskdetail

import app.ytak.tasks.base.data.flux.action.TaskAction

interface TaskLoader {

    val taskAction: TaskAction

    fun loadTask(taskId: String) {
        taskAction.loadTask(taskId)
    }
}
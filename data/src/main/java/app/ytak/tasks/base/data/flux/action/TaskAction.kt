package app.ytak.tasks.base.data.flux.action

import app.ytak.tasks.base.data.flux.dispatcher.TaskDispatcher
import app.ytak.tasks.base.data.util.ext.filterOptionalNotNull
import app.ytak.tasks.base.data.util.optional
import app.ytak.tasks.common.model.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitFirst
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskAction @Inject constructor(private val dispatcher: TaskDispatcher) {

    fun refreshTasks() = GlobalScope.launch {
        // TODO: Call API
        val tasks = listOf(
            Task("0", "タスクA", "1つ目のタスクです", false),
            Task("1", "タスクB", "2つ目のタスクです", true),
            Task("2", "タスクC", "3つ目のタスクです", false)
        )
        dispatcher.taskDao.replaceAll(tasks)
    }

    fun loadTask(taskId: String) = GlobalScope.launch {
        // TODO: Call API
        val task = dispatcher.taskDao.find(taskId).filterOptionalNotNull().awaitFirst()
        dispatcher.currentTask.onNext(task.optional())
    }

    fun updateCurrentTask(currentTask: Task) = GlobalScope.launch {
        dispatcher.currentTask.onNext(currentTask.optional())
    }

    fun createTask(task: Task) = GlobalScope.launch {
        // TODO: Call API
        dispatcher.taskDao.upsert(task)
    }

    fun deleteTask(taskId: String) = GlobalScope.launch {
        // TODO: Call API
        dispatcher.taskDao.delete(taskId)
    }

    fun updateDoneStatus(taskId: String, isDone: Boolean) = GlobalScope.launch {
        // TODO: Call API
        val task = dispatcher.taskDao.find(taskId).filterOptionalNotNull().awaitFirst()
        dispatcher.taskDao.upsert(Task(task.id, task.title, task.description, isDone))
    }
}
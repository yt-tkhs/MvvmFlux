package app.ytak.tasks.base.data.flux.store

import app.ytak.tasks.base.data.flux.dispatcher.TaskDispatcher
import app.ytak.tasks.base.data.util.CommonStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskStore @Inject constructor(private val dispatcher: TaskDispatcher) : CommonStore(dispatcher) {

    fun tasks() = dispatcher.taskDao.findAll()

    fun currentTask() = dispatcher.currentTask
}
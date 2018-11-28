package app.ytak.tasks.base.data.flux.dispatcher

import app.ytak.tasks.base.data.db.DaoProvider
import app.ytak.tasks.base.data.db.dao.TaskDao
import app.ytak.tasks.base.data.util.CommonDispatcher
import app.ytak.tasks.base.data.util.Optional
import app.ytak.tasks.common.model.Task
import io.reactivex.processors.BehaviorProcessor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDispatcher @Inject constructor(private val daoProvider: DaoProvider) : CommonDispatcher() {

    val taskDao = daoProvider.get(TaskDao::class.java)

    val currentTask = BehaviorProcessor.createDefault(Optional.empty<Task>())
}
package app.ytak.tasks.base.data.flux.dispatcher

import app.ytak.tasks.base.data.db.DaoProvider
import app.ytak.tasks.base.data.db.dao.TaskDao
import app.ytak.tasks.base.data.util.CommonDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskDispatcher @Inject constructor(private val daoProvider: DaoProvider) : CommonDispatcher() {

    val taskDao = daoProvider.get(TaskDao::class.java)
}
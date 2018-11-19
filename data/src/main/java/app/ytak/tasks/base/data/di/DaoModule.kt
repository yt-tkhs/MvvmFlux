package app.ytak.tasks.base.data.di

import app.ytak.tasks.base.data.db.dao.TaskDao
import app.ytak.tasks.base.data.util.SqlDao
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DaoModule {

    @Binds
    @IntoMap
    @DaoKey(TaskDao::class)
    abstract fun bindTaskDao(taskDao: TaskDao): SqlDao<out SqlDao.Dto>
}
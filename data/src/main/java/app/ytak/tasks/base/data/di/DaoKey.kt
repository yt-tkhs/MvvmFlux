package app.ytak.tasks.base.data.di

import app.ytak.tasks.base.data.util.SqlDao
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class DaoKey(val value: KClass<out SqlDao<out SqlDao.Dto>>)

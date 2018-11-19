package app.ytak.tasks.base.data.db

import app.ytak.tasks.base.data.util.SqlDao
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class DaoProvider @Inject constructor(
    private val daoMap: Map<Class<out SqlDao<out SqlDao.Dto>>, @JvmSuppressWildcards Provider<SqlDao<out SqlDao.Dto>>>
) {

    @Suppress("UNCHECKED_CAST")
    fun <T : SqlDao<out SqlDao.Dto>> get(daoClass: Class<T>): T = daoMap[daoClass] as? T ?: throw RuntimeException()
}
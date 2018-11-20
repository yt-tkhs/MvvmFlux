package app.ytak.tasks.base.data.util

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.squareup.sqlbrite3.BriteDatabase
import com.squareup.sqlbrite3.SqlBrite
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.functions.Function
import io.reactivex.processors.PublishProcessor

abstract class SqlDao<T : SqlDao.Dto> protected constructor(protected val db: BriteDatabase) {

    companion object {
        const val TRUE = 1
        const val DELIMITER = "_,_"
    }

    open class Dto {
        open val id: Int? = null
    }

    protected val emptyFlowable = PublishProcessor.create<Unit>()

    private val mapFunc = Function<SqlBrite.Query, List<T>> { query ->
        val c = query.run() ?: return@Function null
        val cursor = SqlCursor(c)
        cursor.use {
            val values = mutableListOf<T>()
            while (cursor.moveToNext()) {
                values.add(toObject(cursor))
            }
            return@Function values
        }
    }

    abstract fun toObject(cursor: SqlCursor): T

    abstract fun toContentValues(dto: T): ContentValues

    fun listToString(list: List<String>?): String? {
        if (list == null || list.isEmpty()) {
            return null
        }
        val sb = StringBuilder()
        for (s in list) {
            sb.append(s).append(DELIMITER)
        }
        return sb.toString().substring(0, sb.lastIndexOf(DELIMITER))
    }

    protected fun booleanToInt(b: Boolean): Int {
        return if (b) TRUE else 0
    }

    protected fun createQuery(table: String, sql: String, vararg args: String): Flowable<List<T>> {
        return db.createQuery(table, sql, *args).map(mapFunc).toFlowable(BackpressureStrategy.LATEST)
    }

    protected fun insert(table: String, obj: T): Long {
        return db.insert(table, SQLiteDatabase.CONFLICT_REPLACE, toContentValues(obj))
    }

    protected fun upsert(table: String, obj: T, whereClause: String, vararg whereArgs: String): Long {
        val row = db.update(table, SQLiteDatabase.CONFLICT_REPLACE, toContentValues(obj), whereClause, *whereArgs)
        return if (row >= 1) row.toLong()
        else db.insert(table, SQLiteDatabase.CONFLICT_REPLACE, toContentValues(obj))
    }

    protected fun delete(table: String, whereClause: String, vararg whereArgs: String): Int {
        return db.delete(table, whereClause, *whereArgs)
    }

    protected fun deleteAll(table: String): Int {
        return db.delete(table, "")
    }
}

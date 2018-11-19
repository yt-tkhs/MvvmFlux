package app.ytak.tasks.base.data.util

import android.database.Cursor
import android.database.CursorWrapper
import java.util.*


class SqlCursor(cursor: Cursor) : CursorWrapper(cursor) {

    fun getDouble(columnName: String): Double = getDouble(getColumnIndexOrThrow(columnName))

    fun getFloat(columnName: String): Float = getFloat(getColumnIndexOrThrow(columnName))

    fun getInt(columnName: String): Int = getInt(getColumnIndexOrThrow(columnName))

    fun getLong(columnName: String): Long = getLong(getColumnIndexOrThrow(columnName))

    fun getShort(columnName: String): Short = getShort(getColumnIndexOrThrow(columnName))

    fun getString(columnName: String): String = getString(getColumnIndexOrThrow(columnName))

    fun getBoolean(columnName: String): Boolean = getInt(columnName) == SqlDao.TRUE

    fun getStringList(columnName: String): List<String>? =
        Arrays.asList(*getString(columnName)
            .split(SqlDao.DELIMITER.toRegex())
            .dropLastWhile { it.isEmpty() }
            .toTypedArray())
}

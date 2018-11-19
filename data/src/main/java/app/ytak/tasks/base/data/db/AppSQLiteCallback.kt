package app.ytak.tasks.base.data.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.db.SupportSQLiteOpenHelper
import app.ytak.tasks.base.data.db.dao.TaskDao

class AppSQLiteCallback : SupportSQLiteOpenHelper.Callback(VERSION) {

    companion object {
        private const val VERSION = 1
    }

    override fun onCreate(db: SupportSQLiteDatabase) {
        TaskDao.createTable(db)
    }

    override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // no op
    }
}
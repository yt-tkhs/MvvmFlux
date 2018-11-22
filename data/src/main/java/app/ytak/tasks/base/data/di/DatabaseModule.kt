package app.ytak.tasks.base.data.di

import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import android.content.Context
import app.ytak.tasks.base.data.db.AppSQLiteCallback
import com.squareup.sqlbrite3.BriteDatabase
import com.squareup.sqlbrite3.SqlBrite
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideSqlBrite(): SqlBrite = SqlBrite.Builder().logger { Timber.tag("SqlBrite").v(it) }.build()

    @Singleton
    @Provides
    fun provideSQLiteCallback(): AppSQLiteCallback = AppSQLiteCallback()

    @Singleton
    @Provides
    fun briteDataBase(context: Context, sqlBrite: SqlBrite, callback: AppSQLiteCallback): BriteDatabase {
        val configuration = SupportSQLiteOpenHelper.Configuration.builder(context)
            .name(context.packageName)
            .callback(callback)
            .build()
        val helper = FrameworkSQLiteOpenHelperFactory().create(configuration)
        return sqlBrite.wrapDatabaseHelper(helper, Schedulers.single())
    }
}
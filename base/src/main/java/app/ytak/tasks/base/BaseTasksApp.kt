package app.ytak.tasks.base

import com.facebook.stetho.Stetho
import dagger.android.support.DaggerApplication
import timber.log.Timber

abstract class BaseTasksApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)
    }
}
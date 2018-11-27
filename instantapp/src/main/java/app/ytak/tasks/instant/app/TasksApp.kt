package app.ytak.tasks.instant.app

import app.ytak.tasks.base.BaseTasksApp
import app.ytak.tasks.base.data.di.DaggerDataComponent
import app.ytak.tasks.instant.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class TasksApp : BaseTasksApp() {

    private val dataComponent by lazy { DaggerDataComponent.builder().context(this).build() }
    private val appComponent by lazy { DaggerAppComponent.builder().dataComponent(dataComponent).build() }

    override fun onCreate() {
        super.onCreate()
        Timber.d("yt/ TasksApp")
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}
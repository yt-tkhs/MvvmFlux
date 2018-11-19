package app.ytak.tasks.installed.app

import app.ytak.tasks.base.data.di.DaggerDataComponent
import app.ytak.tasks.base.data.di.DataComponent
import app.ytak.tasks.installed.app.di.AppComponent
import app.ytak.tasks.installed.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class TasksApp : DaggerApplication() {

    private val dataComponent: DataComponent by lazy { DaggerDataComponent.builder().application(this).build() }

    private val appComponent: AppComponent by lazy { DaggerAppComponent.builder().dataComponent(dataComponent).build() }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}
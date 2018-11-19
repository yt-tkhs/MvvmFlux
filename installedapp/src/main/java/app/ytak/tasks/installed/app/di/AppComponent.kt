package app.ytak.tasks.installed.app.di

import app.ytak.tasks.base.data.di.DataComponent
import app.ytak.tasks.base.data.di.DataComponentModules
import app.ytak.tasks.base.di.scope.PerModuleScope
import app.ytak.tasks.installed.app.TasksApp
import app.ytak.tasks.installed.app.di.module.ActivityModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerModuleScope
@Component(
    dependencies = [
        DataComponent::class
    ],
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<TasksApp>, DataComponentModules {

    override fun inject(instance: TasksApp)
}
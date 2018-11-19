package app.ytak.tasks.installed.app.di.module

import app.ytak.tasks.base.di.scope.PerFragmentScope
import app.ytak.tasks.feature.tasklist.TaskListFragment
import app.ytak.tasks.feature.tasklist.di.TaskListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @PerFragmentScope
    @ContributesAndroidInjector(modules = [TaskListModule::class])
    internal abstract fun contributeTaskListFragment(): TaskListFragment
}
package app.ytak.tasks.installed.app.di.module

import app.ytak.tasks.base.di.scope.PerFragmentScope
import app.ytak.tasks.feature.tasklist.TaskListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @PerFragmentScope
    @ContributesAndroidInjector
    fun contributeTaskListFragment(): TaskListFragment
}
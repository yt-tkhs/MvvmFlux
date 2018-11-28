package app.ytak.tasks.instant.app.di.module

import app.ytak.tasks.base.di.scope.PerFragmentScope
import app.ytak.tasks.instant.app.taskdetail.TaskDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @PerFragmentScope
    @ContributesAndroidInjector
    fun contributeTaskDetailFragment(): TaskDetailFragment
}
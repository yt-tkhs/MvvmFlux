package app.ytak.tasks.feature.tasklist.di

import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.di.scope.PerFragmentScope
import app.ytak.tasks.feature.tasklist.TaskListViewModel
import dagger.Module
import dagger.Provides

@Module
class TaskListModule {

    @PerFragmentScope
    @Provides
    fun provideTaskListViewModel(taskAction: TaskAction, taskStore: TaskStore) =
        TaskListViewModel(taskAction, taskStore)
}
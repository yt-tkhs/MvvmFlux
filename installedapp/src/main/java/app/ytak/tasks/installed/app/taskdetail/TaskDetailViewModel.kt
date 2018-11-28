package app.ytak.tasks.installed.app.taskdetail

import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.di.scope.PerActivityScope
import app.ytak.tasks.feature.taskdetail.BaseTaskDetailViewModel
import javax.inject.Inject

@PerActivityScope
class TaskDetailViewModel @Inject constructor(
    taskAction: TaskAction,
    taskStore: TaskStore
) : BaseTaskDetailViewModel(taskAction, taskStore)
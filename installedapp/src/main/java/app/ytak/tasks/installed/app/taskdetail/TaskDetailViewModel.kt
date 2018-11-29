package app.ytak.tasks.installed.app.taskdetail

import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.di.scope.PerActivityScope
import app.ytak.tasks.feature.taskdetail.BaseTaskDetailViewModel
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import javax.inject.Inject

@PerActivityScope
class TaskDetailViewModel @Inject constructor(
    taskAction: TaskAction,
    taskStore: TaskStore
) : BaseTaskDetailViewModel(taskAction, taskStore) {

    private val viewerState = BehaviorProcessor.create<ViewerState>()

    fun viewerState(): FlowableProcessor<ViewerState> = viewerState

    fun setViewerState(viewerState: ViewerState) {
        this.viewerState.onNext(viewerState)
    }
}
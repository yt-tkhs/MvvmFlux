package app.ytak.tasks.feature.tasklist

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import app.ytak.tasks.base.data.flux.action.TaskAction
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.di.scope.PerFragmentScope
import timber.log.Timber
import javax.inject.Inject

@PerFragmentScope
class TaskListViewModel @Inject constructor(
    private val taskAction: TaskAction,
    private val taskStore: TaskStore
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        Timber.d("yt/ onCreate")
    }
}
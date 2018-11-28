package app.ytak.tasks.installed.app

import androidx.lifecycle.LifecycleObserver
import app.ytak.tasks.base.data.flux.store.TaskStore
import app.ytak.tasks.base.data.util.ext.filterOptionalNotNull
import app.ytak.tasks.base.di.scope.PerActivityScope
import javax.inject.Inject

@PerActivityScope
class MainViewModel @Inject constructor(
    private val taskStore: TaskStore
) : LifecycleObserver {

    val currentTask by lazy { taskStore.currentTask().filterOptionalNotNull() }
}
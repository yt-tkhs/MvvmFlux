package app.ytak.tasks.installed.app.taskdetail

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import app.ytak.tasks.base.util.ext.observeOnMain
import app.ytak.tasks.base.util.ext.onPreDraw
import app.ytak.tasks.common.model.Task
import app.ytak.tasks.feature.taskdetail.BaseTaskDetailView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitFirst
import javax.inject.Inject

class TaskDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    BaseTaskDetailView(context, attrs, defStyle) {

    private val behavior = TaskDetailBehavior(this)

    @Inject lateinit var viewModel: TaskDetailViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        onPreDraw { behavior.toNone(true) }

        behavior.onStateChange = { viewModel.setViewerState(it) }

        closeButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                when (viewModel.viewerState().awaitFirst()) {
                    ViewerState.NORMAL -> behavior.toMini()
                    ViewerState.MINI -> behavior.toNone(false)
                    else -> {
                        /* no op */
                    }
                }
            }
        }

        viewModel.currentTask.observeOnMain(this) { task ->
            if (currentTask?.id != task.id || currentTask?.isDone == task.isDone) behavior.toNormal()
            currentTask = task
        }

        isFocusableInTouchMode = true
        requestFocus()
        setOnKeyListener { _, _, event ->
            if (event.action == KeyEvent.ACTION_UP && event.keyCode == KeyEvent.KEYCODE_BACK) {
                behavior.toMini()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    override fun onCheckedChange(task: Task, isChecked: Boolean) {
        viewModel.updateDoneStatus(task.id, isChecked)
    }
}
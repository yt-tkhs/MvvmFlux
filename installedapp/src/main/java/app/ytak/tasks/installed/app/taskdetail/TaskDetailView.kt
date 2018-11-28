package app.ytak.tasks.installed.app.taskdetail

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.ViewTreeObserver
import app.ytak.tasks.base.util.ext.observeOnMain
import app.ytak.tasks.feature.taskdetail.BaseTaskDetailView
import timber.log.Timber
import javax.inject.Inject

class TaskDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    BaseTaskDetailView(context, attrs, defStyle) {

    private val behavior by lazy { TaskDetailBehavior(this) }

    @Inject lateinit var viewModel: TaskDetailViewModel

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                viewTreeObserver.removeOnPreDrawListener(this)
                behavior.toNone(true)
                return true
            }
        })
        viewModel.currentTask.observeOnMain(this) {
            Timber.d("yt/ currentTask observeOnMain: $it")
            currentTask = it
            behavior.toNormal()
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
}
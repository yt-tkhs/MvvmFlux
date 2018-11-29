package app.ytak.tasks.feature.taskdetail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import app.ytak.tasks.common.model.Task
import app.ytak.tasks.feature.taskdetail.item.DoneSwitchItem
import app.ytak.tasks.feature.taskdetail.item.HeaderItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.view_task_detail.view.closeButton
import kotlinx.android.synthetic.main.view_task_detail.view.mainLayout
import kotlinx.android.synthetic.main.view_task_detail.view.recyclerView
import kotlinx.android.synthetic.main.view_task_detail.view.shadowView
import timber.log.Timber

abstract class BaseTaskDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    ConstraintLayout(context, attrs, defStyle) {

    var currentTask: Task? = null
        set(value) {
            field = value
            setTaskInternal(value)
        }

    private val adapter = GroupAdapter<ViewHolder>()
    private val section = Section().also(adapter::add)

    val mainLayout: ConstraintLayout by lazy { rootView.mainLayout }
    val recyclerView: RecyclerView by lazy { rootView.recyclerView }
    val shadowView: View by lazy { rootView.shadowView }
    val closeButton: ImageButton by lazy { rootView.closeButton }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_task_detail, this, true)
        recyclerView.run {
            adapter = this@BaseTaskDetailView.adapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }
    }

    private fun setTaskInternal(task: Task?) {
        task ?: run {
            Timber.w("currentTask is null")
            section.update(listOf())
            return
        }
        section.update(
            listOf(
                HeaderItem(task),
                DoneSwitchItem(task) { isChecked ->
                    onCheckedChange(task, isChecked)
                }
            )
        )
    }

    open fun onCheckedChange(task: Task, isChecked: Boolean) {
        // no op
    }
}
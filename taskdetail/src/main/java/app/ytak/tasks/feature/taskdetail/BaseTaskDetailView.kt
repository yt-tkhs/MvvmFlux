package app.ytak.tasks.feature.taskdetail

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import app.ytak.tasks.common.model.Task
import app.ytak.tasks.feature.taskdetail.item.DoneSwitchItem
import app.ytak.tasks.feature.taskdetail.item.HeaderItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.view_task_detail.view.mainLayout
import kotlinx.android.synthetic.main.view_task_detail.view.recyclerView
import kotlinx.android.synthetic.main.view_task_detail.view.shadowView
import timber.log.Timber

open class BaseTaskDetailView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    ConstraintLayout(context, attrs, defStyle) {

    var currentTask: Task? = null
        set(value) = setTaskInternal(value)

    var onCheckedChangeListener: ((Task, Boolean) -> Unit)? = null

    private val adapter = GroupAdapter<ViewHolder>()
    private val section = Section().also(adapter::add)

    val mainLayout: ConstraintLayout by lazy { rootView.mainLayout }
    val recyclerView: RecyclerView by lazy { rootView.recyclerView }
    val shadowView: View by lazy { rootView.shadowView }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_task_detail, this, true)
        recyclerView.run {
            adapter = this@BaseTaskDetailView.adapter
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }
    }

    protected fun setTaskInternal(task: Task?) {
        task ?: run {
            Timber.w("currentTask is null")
            section.update(listOf())
            return
        }
        section.update(
            listOf(
                HeaderItem(task),
                DoneSwitchItem(task) { isChecked ->
                    onCheckedChangeListener?.invoke(task, isChecked)
                }
            )
        )
    }
}
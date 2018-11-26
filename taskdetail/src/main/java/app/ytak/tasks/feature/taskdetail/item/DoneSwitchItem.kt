package app.ytak.tasks.feature.taskdetail.item

import app.ytak.tasks.common.model.Task
import app.ytak.tasks.feature.taskdetail.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_status.view.doneSwitch

class DoneSwitchItem(task: Task, private val onCheckedChange: ((Boolean) -> Unit)? = null) :
    Item(task.id.hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.item_status

    private var isChecked = task.isDone

    override fun bind(viewHolder: ViewHolder, position: Int) = viewHolder.itemView.run {
        doneSwitch.isChecked = isChecked
        doneSwitch.setOnCheckedChangeListener { _, isChecked ->
            this@DoneSwitchItem.isChecked = isChecked
            onCheckedChange?.invoke(isChecked)
        }
    }
}
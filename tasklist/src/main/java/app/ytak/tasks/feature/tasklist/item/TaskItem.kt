package app.ytak.tasks.feature.tasklist.item

import app.ytak.tasks.common.model.Task
import app.ytak.tasks.feature.tasklist.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_task.view.descriptionText
import kotlinx.android.synthetic.main.item_task.view.doneCheckBox
import kotlinx.android.synthetic.main.item_task.view.titleText

class TaskItem(
    private val task: Task,
    private val onClick: () -> Unit,
    private val onCheckedChange: (Boolean) -> Unit
) : Item(task.id.hashCode().toLong()) {

    private var isChecked = task.isDone

    override fun getLayout(): Int = R.layout.item_task

    override fun bind(viewHolder: ViewHolder, position: Int) = viewHolder.itemView.run {
        titleText.text = task.title
        descriptionText.text = task.description
        doneCheckBox.isChecked = isChecked
        doneCheckBox.setOnCheckedChangeListener { _, isChecked ->
            this@TaskItem.isChecked = isChecked
            onCheckedChange(isChecked)
        }
        setOnClickListener { onClick() }
    }
}
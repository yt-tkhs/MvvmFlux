package app.ytak.tasks.feature.taskdetail.item

import app.ytak.tasks.common.model.Task
import app.ytak.tasks.feature.taskdetail.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_header.view.taskDescriptionText
import kotlinx.android.synthetic.main.item_header.view.taskTitleText

class HeaderItem(private val task: Task) : Item(task.id.hashCode().toLong()) {

    override fun getLayout(): Int = R.layout.item_header

    override fun bind(viewHolder: ViewHolder, position: Int) = viewHolder.itemView.run {
        taskTitleText.text = task.title
        taskDescriptionText.text = task.description
    }
}
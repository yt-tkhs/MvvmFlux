package app.ytak.tasks.feature.taskdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.SimpleItemAnimator
import app.ytak.tasks.feature.taskdetail.item.DoneSwitchItem
import app.ytak.tasks.feature.taskdetail.item.HeaderItem
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.autoDisposable
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_task_detail.recyclerView
import javax.inject.Inject

class TaskDetailFragment : DaggerFragment() {

    private val section = Section()

    @Inject lateinit var viewModel: TaskDetailViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        viewModel.initialize(TaskDetailFragmentArgs.fromBundle(arguments).taskId)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.run {
            adapter = GroupAdapter<ViewHolder>().apply { add(section) }
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }

        viewModel.task
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(scope())
            .subscribe { task ->
                section.update(
                    listOf(
                        HeaderItem(task),
                        DoneSwitchItem(task) { isChecked ->
                            viewModel.updateDoneStatus(task.id, isChecked)
                        }
                    )
                )
            }
    }
}
package app.ytak.tasks.feature.tasklist

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ytak.tasks.feature.tasklist.item.TaskItem
import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.autoDisposable
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_task_list.recyclerView
import javax.inject.Inject

class TaskListFragment : DaggerFragment() {

    @Inject lateinit var viewModel: TaskListViewModel

    private val adapter = GroupAdapter<ViewHolder>()
    private val section = Section().also(adapter::add)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        lifecycle.addObserver(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.run {
            adapter = this@TaskListFragment.adapter
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
        }

        viewModel.tasks
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(scope())
            .subscribe { tasks ->
                section.update(tasks.map { task ->
                    TaskItem(task) { isChecked ->
                        viewModel.updateDoneStatus(task.id, isChecked)
                    }
                })
            }
    }
}
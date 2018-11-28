package app.ytak.tasks.instant.app.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.ytak.tasks.base.util.ext.observeOnMain
import app.ytak.tasks.instant.app.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_task_detail.taskDetailView
import timber.log.Timber
import javax.inject.Inject

class TaskDetailFragment : DaggerFragment() {

    private val args by lazy { TaskDetailFragmentArgs.fromBundle(arguments) }

    @Inject lateinit var viewModel: TaskDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("yt/ onViewCreated")

        viewModel.currentTask.observeOnMain(this) {
            Timber.d("yt/ currentTask=$it")
            taskDetailView.currentTask = it
        }

        viewModel.refreshTask(args.taskId)
    }
}
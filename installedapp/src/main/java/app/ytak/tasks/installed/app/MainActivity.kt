package app.ytak.tasks.installed.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import app.ytak.tasks.installed.app.taskdetail.TaskDetailView
import app.ytak.tasks.installed.app.taskdetail.TaskDetailViewModel
import app.ytak.tasks.installed.app.taskdetail.ViewerState
import dagger.MembersInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.taskDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitFirst
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var viewModel: MainViewModel
    @Inject lateinit var taskDetailViewModel: TaskDetailViewModel

    @Inject lateinit var taskDetailViewInjector: MembersInjector<TaskDetailView>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(taskDetailViewModel)
        taskDetailViewInjector.injectMembers(taskDetailView)
    }

    override fun onBackPressed() {
        GlobalScope.launch {
            val viewerState = taskDetailViewModel.viewerState().awaitFirst() ?: return@launch
            when (viewerState) {
                ViewerState.NORMAL -> taskDetailViewModel.setViewerState(ViewerState.NORMAL)
                ViewerState.NONE, ViewerState.MINI -> super.onBackPressed()
            }
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = fragmentInjector
}

package app.ytak.tasks.instant.app

import android.os.Bundle
import androidx.navigation.findNavController
import app.ytak.tasks.instant.app.taskdetail.TaskDetailFragmentArgs
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.data?.lastPathSegment?.let { taskId ->
            navController.setGraph(
                R.navigation.graph_main,
                TaskDetailFragmentArgs.Builder(taskId).build().toBundle()
            )
        } ?: run {
            // TODO Error handling
        }
    }
}

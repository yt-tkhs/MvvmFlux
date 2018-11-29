package app.ytak.tasks.base.util.ext

import app.ytak.tasks.base.util.JobContainer
import kotlinx.coroutines.Job

fun Job.addTo(jobContainer: JobContainer) = jobContainer.add(this)
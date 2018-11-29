package app.ytak.tasks.base.util

import kotlinx.coroutines.Job

class JobContainer {

    private val jobs = HashSet<Job>()

    val isAllCompleted: Boolean get() = jobs.all { it.isCompleted }

    fun add(job: Job) = jobs.add(job)

    fun cancel() = jobs.forEach { it.cancel() }

}

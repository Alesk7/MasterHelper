package alesk.com.masterhelper.presentation.project.jobs

import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.domain.interactor.JobsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import javax.inject.Inject

class JobsPresenter @Inject constructor(
        private val jobsInteractor: JobsInteractor
): BasePresenter<JobsView, JobsRouter>() {

    lateinit var jobsList: List<Job>

    override fun onStart() {
        updateViewJobsList()
    }

    fun onAddJob(){
        view?.showAddJobDialog { name, quantity, unit ->
            if(name.isBlank()) return@showAddJobDialog
            val job = Job(name, quantity ?: 1.0, 0.0, unit, 0.0, false)
            job.projectId = view!!.getProjectId()
            jobsInteractor.addJob(job)
            updateViewJobsList()
            view?.updateViewBindings()
        }
    }

    fun onDeleteJob(job: Job, position: Int){
        view?.askForDeleting(job, position)
    }

    fun deleteJob(job: Job, position: Int) {
        jobsInteractor.deleteJob(job)
        updateViewJobsList()
        view?.notifyItemRemoved(position)
    }

    fun onJobStatusChanged(isChecked: Boolean, job: Job){
        job.isComplete = isChecked
        jobsInteractor.editJob(job)
    }

    private fun updateViewJobsList() {
        jobsList = jobsInteractor.getJobsByProjectId(view!!.getProjectId())
        view?.setJobsList(jobsList)
    }

}
package alesk.com.masterhelper.presentation.project.jobs

import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.domain.interactor.JobsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import javax.inject.Inject

class JobsPresenter @Inject constructor(
        val jobsInteractor: JobsInteractor
): BasePresenter<JobsView, JobsRouter>() {

    lateinit var jobsList: List<Job>

    override fun onStart() {
        jobsList = jobsInteractor.getJobsByProjectId(view!!.getProjectId())
        view?.setJobsList(jobsList)
    }

    fun onAddJob(){
        view?.showAddJobDialog({ name, quantity, unit ->
            val job = Job(name, quantity ?: 1.0, 0.0, unit, 0.0, false)
            job.projectId = view!!.getProjectId()
            jobsInteractor.addJob(job)
            jobsList = jobsInteractor.getJobsByProjectId(view!!.getProjectId())
            view?.setJobsList(jobsList)
            view?.updateViewBindings()
        })
    }

    fun onEditJob(job: Job, position: Int){
        view?.showEditJobDialog(job.name, job.quantity, job.unit, { name, quantity, unit ->
            job.name = name
            job.quantity = quantity ?: 1.0
            job.unit = unit
            jobsInteractor.editJob(job)
            view?.notifyItemChanged(position)
        })
    }

    fun onJobStatusChanged(isChecked: Boolean, job: Job){
        job.isComplete = isChecked
        jobsInteractor.editJob(job)
    }

}
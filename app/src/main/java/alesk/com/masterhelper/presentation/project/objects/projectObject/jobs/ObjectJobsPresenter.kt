package alesk.com.masterhelper.presentation.project.objects.projectObject.jobs

import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.domain.interactor.JobsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectRouter
import javax.inject.Inject

class ObjectJobsPresenter @Inject constructor(
        val jobsInteractor: JobsInteractor
): BasePresenter<ObjectJobsView, ObjectRouter>() {

    lateinit var jobsList: List<Job>
    var objectId: Long = 0

    override fun onStart() {
        objectId = view!!.getObjectId()
        updateViewJobList()
    }

    fun updateViewJobList(){
        jobsList = jobsInteractor.getJobsByObjectId(objectId)
        view?.setJobsList(jobsList)
    }

    fun onAddJobBinding(){
        val availableJobs = jobsInteractor
                .getJobsByProjectId(view!!.getProjectId()).filter { it.objectId != objectId }
        view?.showAddJobBindingDialog(availableJobs)
    }

    fun onJobClicked(job: Job) {
    }

    fun onAddJobClicked(job: Job){
        job.objectId = objectId
        jobsInteractor.editJob(job)
        updateViewJobList()
        view?.notifyDataSetChanged()
        view?.hideAddJobBindingDialog()
    }

    fun onCreateJobClicked(){
        view?.showCreateJobDialog({ name, quantity, unit ->
            val job = Job(name = name, quantity = quantity ?: 0.0, unit = unit)
            job.projectId = view!!.getProjectId()
            job.objectId = objectId
            jobsInteractor.addJob(job)
            updateViewJobList()
            view?.notifyDataSetChanged()
            view?.hideAddJobBindingDialog()
        })
    }

}
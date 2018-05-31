package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.domain.repository.JobRepository
import javax.inject.Inject

class JobsInteractor @Inject constructor(val jobRepository: JobRepository) {

    fun getJobsByProjectId(projectId: Long): List<Job> {
        return jobRepository.getJobsByProjectId(projectId).asReversed()
    }

    fun getJobsByObjectId(objectId: Long): List<Job> {
        return jobRepository.getJobsByObjectId(objectId).asReversed()
    }

    fun addJob(job: Job){
        jobRepository.addJob(job)
    }

    fun editJob(job: Job){
        jobRepository.editJob(job)
    }

}
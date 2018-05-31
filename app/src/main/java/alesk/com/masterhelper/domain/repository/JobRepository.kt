package alesk.com.masterhelper.domain.repository

import alesk.com.masterhelper.data.entities.Job

interface JobRepository {
    fun getJob(id: Long): Job?
    fun getJobsByProjectId(projectId: Long): List<Job>
    fun getJobsByObjectId(objectId: Long): List<Job>
    fun addJob(job: Job)
    fun editJob(job: Job)
}
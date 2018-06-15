package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.dao.JobDAO
import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.domain.repository.JobRepository
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
        private val jobDAO: JobDAO
): JobRepository {

    override fun getJob(id: Long): Job? {
        return jobDAO.getJob(id)
    }

    override fun getJobsByProjectId(projectId: Long): List<Job> {
        return jobDAO.getJobsByProjectId(projectId)
    }

    override fun getJobsByObjectId(objectId: Long): List<Job> {
        return jobDAO.getJobsByObjectId(objectId)
    }

    override fun addJob(job: Job) {
        jobDAO.addJob(job)
    }

    override fun editJob(job: Job) {
        jobDAO.editJob(job)
    }

}
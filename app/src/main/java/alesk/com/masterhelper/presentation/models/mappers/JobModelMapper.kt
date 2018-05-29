package alesk.com.masterhelper.presentation.models.mappers

import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.presentation.models.JobModel
import javax.inject.Inject

class JobModelMapper @Inject constructor() {

    fun transform(job: Job): JobModel {
        return JobModel(
                job.id,
                job.projectId,
                job.name,
                job.quantity,
                job.unitPrice,
                job.unit,
                job.priceSum,
                job.isComplete
        )
    }

    fun transform(jobModel: JobModel): Job {
        val job = Job(
                jobModel.name,
                jobModel.quantity,
                jobModel.unitPrice,
                jobModel.unit,
                jobModel.priceSum,
                jobModel.isComplete
        )
        job.id = jobModel.id
        job.projectId = jobModel.projectId
        return job
    }

}
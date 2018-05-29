package alesk.com.masterhelper.presentation.project.prices.jobPrices

import alesk.com.masterhelper.presentation.models.JobModel

interface JobPricesView {
    fun getProjectId(): Long
    fun setJobsList(items: List<JobModel>)
}
package alesk.com.masterhelper.presentation.project.prices.jobPrices

import alesk.com.masterhelper.domain.interactor.JobsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.JobModel
import alesk.com.masterhelper.presentation.models.mappers.JobModelMapper
import alesk.com.masterhelper.presentation.project.prices.PricesRouter
import javax.inject.Inject

class JobPricesPresenter @Inject constructor(
        private val jobsInteractor: JobsInteractor,
        private val jobModelMapper: JobModelMapper
): BasePresenter<JobPricesView, PricesRouter>() {

    lateinit var jobsList: List<JobModel>

    override fun onStart() {
        jobsList = jobsInteractor.getJobsByProjectId(view!!.getProjectId()).map {
            jobModelMapper.transform(it)
        }
        view?.setJobsList(jobsList)
    }

    fun onPriceChanged(jobModel: JobModel){
        jobsInteractor.editJob(jobModelMapper.transform(jobModel))
    }

}
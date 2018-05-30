package alesk.com.masterhelper.presentation.project.prices.jobPrices

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.FragmentJobPricesBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.JobModel
import alesk.com.masterhelper.presentation.project.prices.PricesRouter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class JobPricesFragment: BaseFragment<JobPricesPresenter, JobPricesView, PricesRouter>(), JobPricesView {

    lateinit var binding: FragmentJobPricesBinding
    lateinit var adapter: JobPricesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_job_prices, container, false)
        adapter = JobPricesAdapter(context, { presenter.onPriceChanged(it) })
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.adapter = adapter
        presenter.onStart()
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun setJobsList(items: List<JobModel>) {
        adapter.items = items
    }

    override fun getProjectId(): Long {
        return activity!!.intent!!.getLongExtra(getString(R.string.keyIdProject), 0)
    }

}
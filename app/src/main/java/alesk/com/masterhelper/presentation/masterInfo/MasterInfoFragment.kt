package alesk.com.masterhelper.presentation.masterInfo

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerMainComponent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.master_info_fragment.*
import kotlinx.android.synthetic.main.master_info_fragment.view.*
import javax.inject.Inject

class MasterInfoFragment : BaseFragment(), MasterInfoView {

    @Inject
    lateinit var presenter: MasterInfoPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.master_info_fragment, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View){
        view.saveButton.setOnClickListener { presenter.onSaveMasterInfo() }
        view.individualButton.setOnClickListener { presenter.setIndividual() }
        view.organizationButton.setOnClickListener { presenter.setOrganization() }
    }

    override fun inject() {
        DaggerMainComponent.create().inject(this)
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.router = activity as MasterInfoRouter
    }

    override fun setIndividualButtonActive() {
        individualButton.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        individualButton.setTextColor(Color.WHITE)
        organizationButton.setBackgroundColor(Color.TRANSPARENT)
        organizationButton.setTextColor(Color.BLACK)
    }

    override fun setOrganizationButtonActive() {
        organizationButton.setBackgroundColor(resources.getColor(R.color.colorAccent))
        organizationButton.setTextColor(Color.WHITE)
        individualButton.setBackgroundColor(Color.TRANSPARENT)
        individualButton.setTextColor(Color.BLACK)
    }

    override fun setIndividualCardVisible() {
        individualInfoCard.visibility = View.VISIBLE
        organizationInfoCard.visibility = View.GONE
    }

    override fun setOrganizationCardVisible() {
        organizationInfoCard.visibility = View.VISIBLE
        individualInfoCard.visibility = View.GONE
    }

}

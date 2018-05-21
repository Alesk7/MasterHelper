package alesk.com.masterhelper.presentation.masterInfo

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.FragmentMasterInfoBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_master_info.*
import javax.inject.Inject

class MasterInfoFragment : BaseFragment(), MasterInfoView {

    @Inject
    lateinit var presenter: MasterInfoPresenter
    lateinit var binding: FragmentMasterInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_master_info, container, false)
        setActionBarTitle(getString(R.string.masterDetails))
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.masterInfo = presenter.masterInfoModel
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.router = activity as MasterInfoRouter
    }

    override fun setIndividualButtonActive() {
        individualButton.setBackgroundColor(resources.getColor(R.color.colorAccentSecondary))
        individualButton.setTextColor(Color.WHITE)
        organizationButton.setBackgroundColor(Color.TRANSPARENT)
        organizationButton.setTextColor(Color.BLACK)
    }

    override fun setOrganizationButtonActive() {
        organizationButton.setBackgroundColor(resources.getColor(R.color.colorAccentSecondary))
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

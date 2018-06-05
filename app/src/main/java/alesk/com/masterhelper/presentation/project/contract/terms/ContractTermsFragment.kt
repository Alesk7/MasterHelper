package alesk.com.masterhelper.presentation.project.contract.terms

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.FragmentContractTermsBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.project.contract.ContractRouter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ContractTermsFragment:
        BaseFragment<ContractTermsPresenter, ContractTermsView, ContractRouter>(), ContractTermsView {

    lateinit var binding: FragmentContractTermsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contract_terms, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.contract = presenter.projectModel.contract
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun getProjectModel(): ProjectModel {
        return activity!!.intent.getSerializableExtra(getString(R.string.keyProjectModel)) as ProjectModel
    }

}
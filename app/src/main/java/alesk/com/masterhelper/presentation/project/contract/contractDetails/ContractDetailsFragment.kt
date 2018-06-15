package alesk.com.masterhelper.presentation.project.contract.contractDetails

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.defaultDateFormat
import alesk.com.masterhelper.databinding.FragmentContractDetailsBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.project.contract.ContractRouter
import android.app.DatePickerDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ContractDetailsFragment:
        BaseFragment<ContractDetailsPresenter, ContractDetailsView, ContractRouter>(), ContractDetailsView {

    lateinit var binding: FragmentContractDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contract_details, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.contract = presenter.projectModel.contract
        binding.dateFormat = defaultDateFormat
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun showDatePicker(dateSet: (year: Int, month: Int, day: Int) -> Unit,
                                startYear: Int, startMonth: Int, startDay: Int) {
        DatePickerDialog(context, { _, year, month, day ->
            dateSet(year, month, day)
        }, startYear, startMonth, startDay).show()
    }

    override fun getProjectModel(): ProjectModel {
        return activity!!.intent.getSerializableExtra(getString(R.string.keyProjectModel)) as ProjectModel
    }

    override fun updateViewBindings() = binding.invalidateAll()

}
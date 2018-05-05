package alesk.com.masterhelper.presentation.project.contractDetails

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.ActivityContractBinding
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ProjectModel
import android.app.DatePickerDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class ContractActivity : AppCompatActivity(), ContractView, ContractRouter {

    @Inject
    lateinit var presenter: ContractPresenter
    lateinit var binding: ActivityContractBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contract)
        supportActionBar?.title = getString(R.string.contractDetails)
        inject()
        initPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.contract = presenter.projectModel.contract
        binding.dateFormat = presenter.dateFormat
    }

    fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    fun initPresenter(){
        presenter.router = this
        presenter.view = this
    }

    override fun showDatePicker(dateSet: (year: Int, month: Int, day: Int) -> Unit,
                                startYear: Int, startMonth: Int, startDay: Int) {
        DatePickerDialog(this, { view, year, month, day ->
            dateSet(year, month, day)
        }, startYear, startMonth, startDay).show()
    }

    override fun getProjectModel(): ProjectModel {
        return intent.getSerializableExtra(getString(R.string.keyProjectModel)) as ProjectModel
    }

    override fun hideContractDetails() = finish()

    override fun updateViewBindings() = binding.invalidateAll()

}

package alesk.com.masterhelper.presentation.project.contractDetails

import alesk.com.masterhelper.application.utils.YEAR_DIFF
import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import javax.inject.Inject

@Suppress("DEPRECATION")
class ContractPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor,
        val projectModelMapper: ProjectModelMapper
) : BasePresenter<ContractView, ContractRouter>() {

    lateinit var projectModel: ProjectModel
    @SuppressLint("SimpleDateFormat")
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onStart() {
        projectModel = view!!.getProjectModel()
    }

    fun onSaveContract(){
        projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
        router?.hideContractDetails()
    }

    fun onMasterSupplier(isChecked: Boolean) {
        projectModel.contract.isMasterMaterialsSupplier = isChecked
    }

    fun onContractDateClicked(){
        view?.showDatePicker({ year, month, day ->
            projectModel.contract.contractDate.year = year - YEAR_DIFF
            projectModel.contract.contractDate.month = month
            projectModel.contract.contractDate.date = day
            view?.updateViewBindings()
        }, YEAR_DIFF + projectModel.contract.contractDate.year,
           projectModel.contract.contractDate.month,
           projectModel.contract.contractDate.date)
    }

    fun onWorkStartDateClicked(){
        view?.showDatePicker({ year, month, day ->
            projectModel.contract.workStartDate.year = year - YEAR_DIFF
            projectModel.contract.workStartDate.month = month
            projectModel.contract.workStartDate.date = day
            view?.updateViewBindings()
        }, YEAR_DIFF + projectModel.contract.workStartDate.year,
                projectModel.contract.workStartDate.month,
                projectModel.contract.workStartDate.date)
    }

    fun onWorkEndDateClicked(){
        view?.showDatePicker({ year, month, day ->
            projectModel.contract.workEndDate.year = year - YEAR_DIFF
            projectModel.contract.workEndDate.month = month
            projectModel.contract.workEndDate.date = day
            view?.updateViewBindings()
        }, YEAR_DIFF + projectModel.contract.workEndDate.year,
                projectModel.contract.workEndDate.month,
                projectModel.contract.workEndDate.date)
    }

}
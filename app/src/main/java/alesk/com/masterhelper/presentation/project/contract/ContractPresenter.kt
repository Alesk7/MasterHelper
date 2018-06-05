package alesk.com.masterhelper.presentation.project.contract

import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import javax.inject.Inject

@Suppress("DEPRECATION")
class ContractPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor,
        val projectModelMapper: ProjectModelMapper
) : BasePresenter<ContractView, ContractRouter>() {

    lateinit var projectModel: ProjectModel

    override fun onStart() {
        projectModel = view!!.getProjectModel()
    }

    fun onSaveContract(){
        projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
        router?.hideContractDetails()
    }

}
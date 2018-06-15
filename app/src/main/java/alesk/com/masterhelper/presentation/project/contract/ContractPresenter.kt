package alesk.com.masterhelper.presentation.project.contract

import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import javax.inject.Inject

class ContractPresenter @Inject constructor(
        private val projectsInteractor: ProjectsInteractor,
        private val projectModelMapper: ProjectModelMapper
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
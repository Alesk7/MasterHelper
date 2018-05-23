package alesk.com.masterhelper.presentation.project.projectInfo

import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import alesk.com.masterhelper.presentation.project.ProjectRouter
import javax.inject.Inject

class ProjectInfoPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor,
        val projectModelMapper: ProjectModelMapper
) : BasePresenter<ProjectInfoView, ProjectRouter>() {

    lateinit var projectModel: ProjectModel

    override fun onStart() {
        projectModel = projectModelMapper.transform(
                projectsInteractor.getProject(view!!.getProjectId()))
    }

    fun onEditProjectDescription(){
        view?.showEditDialog(view!!.getProjectDescriptionString(), projectModel.jobsDescription, {
            projectModel.jobsDescription = it
            view?.updateViewBindings()
            projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
        } )
    }

    fun onEditProjectAddress(){
        view?.showEditDialog(view!!.getProjectAddressString(), projectModel.address, {
            projectModel.address = it
            view?.updateViewBindings()
            projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
        } )
    }

    fun onEditClientInfo(){
        router?.showClientInfo(projectModel)
    }

    fun onClientCall(){
        view?.tryMakeCall(projectModel.client.phoneNumber)
    }

    fun onContractDetailsClicked(){
        router?.showContractDetails(projectModel)
    }

}
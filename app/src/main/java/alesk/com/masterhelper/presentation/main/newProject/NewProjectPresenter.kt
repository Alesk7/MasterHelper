package alesk.com.masterhelper.presentation.main.newProject

import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.main.MainRouter
import alesk.com.masterhelper.presentation.main.newProject.model.ClientModel
import alesk.com.masterhelper.presentation.main.newProject.model.ProjectModel
import alesk.com.masterhelper.presentation.main.newProject.model.mappers.ProjectModelMapper
import javax.inject.Inject

class NewProjectPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor,
        val projectModelMapper: ProjectModelMapper
) : BasePresenter<NewProjectView, MainRouter>() {

    val projectModel = ProjectModel()
    val clientModel = ClientModel()

    override fun onStart() {
    }

    fun onSaveNewProject(){
        projectsInteractor.createNewProject(projectModelMapper.transform(projectModel, clientModel))
    }

    fun setIndividual(){
        view?.setIndividualButtonActive()
        view?.setIndividualCardVisible()
        clientModel.isOrganization = false
    }

    fun setOrganization(){
        view?.setOrganizationButtonActive()
        view?.setOrganizationCardVisible()
        clientModel.isOrganization = true
    }

}
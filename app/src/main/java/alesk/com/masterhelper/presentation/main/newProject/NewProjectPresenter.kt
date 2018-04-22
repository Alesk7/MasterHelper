package alesk.com.masterhelper.presentation.main.newProject

import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.main.MainRouter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import javax.inject.Inject

class NewProjectPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor,
        val projectModelMapper: ProjectModelMapper
) : BasePresenter<NewProjectView, MainRouter>() {

    val projectModel = ProjectModel()

    override fun onStart() {
    }

    fun onSaveNewProject(){
        val project = projectModelMapper.transform(projectModel)
        projectsInteractor.createNewProject(project)
        view?.hide()
    }

    fun setIndividual(){
        view?.setIndividualButtonActive()
        view?.setIndividualCardVisible()
        projectModel.client.isOrganization = false
    }

    fun setOrganization(){
        view?.setOrganizationButtonActive()
        view?.setOrganizationCardVisible()
        projectModel.client.isOrganization = true
    }

}
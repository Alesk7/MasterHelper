package alesk.com.masterhelper.presentation.project.clientInfo

import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import javax.inject.Inject

class ClientInfoPresenter @Inject constructor(
        private val projectsInteractor: ProjectsInteractor,
        private val projectModelMapper: ProjectModelMapper
): BasePresenter<ClientInfoView, ClientInfoRouter>() {

    lateinit var projectModel: ProjectModel

    override fun onStart() {
        projectModel = view!!.getProjectModel()
        if(projectModel.client.isOrganization)
            setOrganization()
    }

    fun onSaveClientInfo(){
        projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
        router?.hideClientInfo()
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
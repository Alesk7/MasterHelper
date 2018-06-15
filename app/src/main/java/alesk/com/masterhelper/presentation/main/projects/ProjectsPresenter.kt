package alesk.com.masterhelper.presentation.main.projects

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.main.MainRouter
import javax.inject.Inject

class ProjectsPresenter @Inject constructor(
        private val interactor: ProjectsInteractor
): BasePresenter<ProjectsView, MainRouter>() {

    lateinit var projectsList: List<Project>

    override fun onStart() {
        projectsList = interactor.getNotCompletedProjects()
        view?.setProjectsList(projectsList)
    }

    fun createNewProject(){
        router?.showCreateNewProject()
    }

    fun onProjectSelected(project: Project){
        router?.showProjectInfo(project.id)
    }

    fun onShowMasterInfo(){
        router?.showMasterInfo()
    }

    fun onShowCompletedProjects(){
        router?.showCompletedProjectsList()
    }

    fun onShowHelp() {
        router?.showHelp()
    }

}
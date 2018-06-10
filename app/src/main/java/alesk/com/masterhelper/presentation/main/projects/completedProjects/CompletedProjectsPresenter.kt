package alesk.com.masterhelper.presentation.main.projects.completedProjects

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.main.MainRouter
import javax.inject.Inject

class CompletedProjectsPresenter @Inject constructor(
        private val projectsInteractor: ProjectsInteractor
): BasePresenter<CompletedProjectsView, MainRouter>() {

    lateinit var projectsList: List<Project>

    override fun onStart() {
        projectsList = projectsInteractor.getCompletedProjects()
        view?.setProjectsList(projectsList)
    }

    fun onProjectSelected(project: Project){
        router?.showProjectInfo(project.id)
    }

}
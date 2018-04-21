package alesk.com.masterhelper.presentation.project.projectInfo

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.project.ProjectRouter
import javax.inject.Inject

class ProjectInfoPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor
) : BasePresenter<ProjectInfoView, ProjectRouter>() {

    lateinit var project: Project

    override fun onStart() {
        project = projectsInteractor.getProject(view!!.getProjectPK())
        view?.setProjectName(project.name)
    }

}
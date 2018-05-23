package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import javax.inject.Inject

class ProjectPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor,
        val projectModelMapper: ProjectModelMapper
) : BasePresenter<ProjectView, ProjectRouter>() {

    lateinit var projectModel: ProjectModel

    override fun onStart() {
        projectModel = projectModelMapper.transform(
                projectsInteractor.getProject(view!!.getProjectId()))
        view?.setProjectName(projectModel.name)
    }

    fun onJobsClicked(){
        router?.showJobs(projectModel.id)
    }

    fun onMaterialsClicked(){
        router?.showMaterials(projectModel.id)
    }

    fun onEditProjectName(){
        view?.showEditDialog(view!!.getProjectNameString(), projectModel.name, {
            if(it.isBlank()) return@showEditDialog
            projectModel.name = it
            view?.setProjectName(projectModel.name)
            projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
        } )
    }

    fun onDeleteProject(){
        projectsInteractor.deleteProject(projectModel.id)
        router?.close()
    }

}
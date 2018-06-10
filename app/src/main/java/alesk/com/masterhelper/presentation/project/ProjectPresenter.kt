package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.domain.interactor.DocumentsInteractor
import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.models.mappers.ProjectModelMapper
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProjectPresenter @Inject constructor(
        val projectsInteractor: ProjectsInteractor,
        val documentsInteractor: DocumentsInteractor,
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

    fun printEstimate() {
        subscribe(documentsInteractor.printPrices(projectModel.id))
    }

    fun printContract() {
        subscribe(documentsInteractor.printContract(projectModel.id))
    }

    fun printAct() {
        subscribe(documentsInteractor.printAct(projectModel.id))
    }

    private fun subscribe(single: Single<String>) = single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showProgressBar() }
                .doAfterTerminate { view?.hideProgressBar() }
                .doAfterSuccess { view?.showDocumentGeneratedDialog(it) }
                .subscribe()

}
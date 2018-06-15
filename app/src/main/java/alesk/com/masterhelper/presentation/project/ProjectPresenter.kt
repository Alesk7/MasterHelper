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
        private val projectsInteractor: ProjectsInteractor,
        private val documentsInteractor: DocumentsInteractor,
        private val projectModelMapper: ProjectModelMapper
) : BasePresenter<ProjectView, ProjectRouter>() {

    lateinit var projectModel: ProjectModel

    override fun onStart() {
        projectModel = projectModelMapper.transform(
                projectsInteractor.getProject(view!!.getProjectId()))
        view?.setProjectName(projectModel.name)
    }

    fun checkIfProjectInArchive() {
        if(projectModel.isComplete) {
            view?.setArchivedColor()
            view?.setUnarchiveMenuItem()
        } else {
            view?.setArchiveMenuItem()
        }
    }

    fun onJobsClicked(){
        router?.showJobs(projectModel.id)
    }

    fun onPricesClicked(){
        router?.showPrices(projectModel.id)
    }

    fun onMaterialsClicked(){
        router?.showMaterials(projectModel.id)
    }

    fun onEditProjectName(){
        view?.showEditDialog(view!!.getProjectNameString(), projectModel.name) {
            if(it.isBlank()) return@showEditDialog
            projectModel.name = it
            view?.setProjectName(projectModel.name)
            projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
        }
    }

    fun onDeleteProject() {
        view?.askForDeleting()
    }

    fun onPrintClicked() {
        view?.showPrintDialog()
    }

    fun onOpenFolderClicked() {
        view?.openDocFolder()
    }

    fun deleteProject(){
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
        projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
    }

    fun onSetArchived() {
        if(!projectModel.isComplete){
            projectModel.isComplete = true
            view?.setArchivedColor()
            view?.setUnarchiveMenuItem()
        } else {
            projectModel.isComplete = false
            view?.setPrimaryColor()
            view?.setArchiveMenuItem()
        }
        projectsInteractor.updateProject(projectModelMapper.transform(projectModel))
    }

    private fun subscribe(single: Single<String>) = single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view?.showProgressBar() }
                .doAfterTerminate { view?.hideProgressBar() }
                .doAfterSuccess { view?.showDocumentGeneratedDialog(it) }
                .subscribe()

}
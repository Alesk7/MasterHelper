package alesk.com.masterhelper.presentation.project.objects

import alesk.com.masterhelper.domain.interactor.ProjectObjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.models.mappers.ObjectModelMapper
import alesk.com.masterhelper.presentation.project.ProjectRouter
import javax.inject.Inject

class ObjectsPresenter @Inject constructor(
        private val projectObjectsInteractor: ProjectObjectsInteractor,
        private val objectModelMapper: ObjectModelMapper
): BasePresenter<ObjectsView, ProjectRouter>() {

    lateinit var objectsList: List<ObjectModel>

    override fun onStart() {
        updateViewObjectsList()
    }

    fun onAddObject(){
        view?.showAddObjectDialog(getSpinnerParentObjectsList()) { name, parent ->
            if(name.isBlank()) return@showAddObjectDialog
            val obj = ObjectModel(projectId = view!!.getProjectId(), name = name)
            if(parent.id != -1L) {
                obj.parentObjectId = parent.id
                obj.parentObjectName = parent.name
            }
            projectObjectsInteractor.addProjectObject(objectModelMapper.transform(obj))
            updateViewObjectsList()
            view?.updateViewBindings()
        }
    }

    fun onObjectClicked(objectModel: ObjectModel){
        router?.showObject(objectModel)
    }

    private fun updateViewObjectsList() {
        objectsList = obtainObjectsList()
        view?.setObjectsList(objectsList)
    }

    private fun obtainObjectsList(): List<ObjectModel> {
        return projectObjectsInteractor.getProjectObjectsByProjectId(view!!.getProjectId()).map {
            val obj = ObjectModel(it.id, it.projectId, it.name, it.parentObjectId)
            if(it.parentObjectId != null) {
                obj.parentObjectName = projectObjectsInteractor.getProjectObject(it.parentObjectId!!).name
            }
            return@map obj
        }
    }

    private fun getSpinnerParentObjectsList(): List<ObjectModel> {
        val parentObjectsList = objectsList.distinct().toMutableList()
        parentObjectsList.add(0, ObjectModel(id = -1, name = view!!.getNoParentObjectString()))
        return parentObjectsList
    }

}
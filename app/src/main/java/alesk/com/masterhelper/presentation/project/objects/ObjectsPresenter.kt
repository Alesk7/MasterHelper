package alesk.com.masterhelper.presentation.project.objects

import alesk.com.masterhelper.domain.interactor.ProjectObjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.models.mappers.ObjectModelMapper
import alesk.com.masterhelper.presentation.project.ProjectRouter
import javax.inject.Inject

class ObjectsPresenter @Inject constructor(
        val projectObjectsInteractor: ProjectObjectsInteractor,
        val objectModelMapper: ObjectModelMapper
): BasePresenter<ObjectsView, ProjectRouter>() {

    lateinit var objectsList: MutableList<ObjectModel>

    override fun onStart() {
        objectsList = projectObjectsInteractor.getProjectObjectsByProjectId(view!!.getProjectId()).map {
            val obj = ObjectModel(it.id, it.projectId, it.name, it.parentObjectId)
            if(it.parentObjectId != null) {
                obj.parentObjectName = projectObjectsInteractor.getProjectObject(it.parentObjectId!!).name
            }
            return@map obj
        }.toMutableList()
        view?.setObjectsList(objectsList)
    }

    fun onAddObject(){
        view?.showAddObjectDialog(getSpinnerParentObjectsList(), { name, parent ->
            if(name.isBlank()) return@showAddObjectDialog
            val obj = ObjectModel(projectId = view!!.getProjectId(), name = name)
            if(parent.id != -1L) {
                obj.parentObjectId = parent.id
                obj.parentObjectName = parent.name
            }
            projectObjectsInteractor.addProjectObject(objectModelMapper.transform(obj))
            objectsList.add(0, obj)
            view?.updateViewBindings()
        })
    }

    private fun getSpinnerParentObjectsList(): List<ObjectModel> {
        val parentObjectsList = objectsList.distinct().toMutableList()
        parentObjectsList.add(0, ObjectModel(id = -1, name = view!!.getNoParentObjectString()))
        return parentObjectsList
    }

}
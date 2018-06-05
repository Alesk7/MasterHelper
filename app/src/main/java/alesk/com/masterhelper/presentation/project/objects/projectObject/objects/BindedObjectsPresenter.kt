package alesk.com.masterhelper.presentation.project.objects.projectObject.objects

import alesk.com.masterhelper.data.entities.ProjectObject
import alesk.com.masterhelper.domain.interactor.ProjectObjectsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.models.mappers.ObjectModelMapper
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectRouter
import javax.inject.Inject

class BindedObjectsPresenter @Inject constructor(
        val projectObjectsInteractor: ProjectObjectsInteractor,
        val objectModelMapper: ObjectModelMapper
): BasePresenter<BindedObjectsView, ObjectRouter>() {

    lateinit var objectModel: ObjectModel
    lateinit var childObjects: List<ProjectObject>
    var parentObjects: MutableList<ProjectObject> = ArrayList()

    override fun onStart() {
        objectModel = view!!.getObject()
        updateViewParentObjectsList()
        updateViewChildObjectsList()
    }

    fun onAddChildObjectClicked(){
        view?.showCreateObjectDialog {
            val newChildObject = ProjectObject(name = it)
            newChildObject.projectId = objectModel.projectId
            newChildObject.parentObjectId = objectModel.id
            projectObjectsInteractor.addProjectObject(newChildObject)
            updateViewChildObjectsList()
        }
    }

    fun onChangeParentObjectClicked(){
        val objects = projectObjectsInteractor
                .getProjectObjectsByProjectId(objectModel.projectId)
                .filter { obj -> (obj.parentObjectId != objectModel.id)
                        && (obj.id != objectModel.id)
                }
        view?.showChangeParentObjectDialog(objects)
    }

    fun onParentObjectSelected(projectObject: ProjectObject) {
        objectModel.parentObjectId = projectObject.id
        objectModel.parentObjectName = projectObject.name
        projectObjectsInteractor.editProjectObject(objectModelMapper.transform(objectModel))
        updateViewParentObjectsList()
        view?.hideChangeParentObjectDialog()
    }

    fun onDeleteChildObjectClicked(child: ProjectObject) {
        view?.askForDeletingChildObject(child)
    }

    fun onDeleteChildObject(child: ProjectObject) {
        projectObjectsInteractor.deleteProjectObject(child.id)
        updateViewChildObjectsList()
    }

    private fun updateViewChildObjectsList(){
        childObjects = projectObjectsInteractor.getProjectObjectsByParentId(objectModel.id)
        view?.setChildObjectsList(childObjects)
        view?.notifyChildDataSetChanged()
    }

    private fun updateViewParentObjectsList(){
        if(objectModel.parentObjectId != null) {
            obtainParentObjects(objectModel.parentObjectId!!)
            view?.showParentObjectView()
            view?.setParentObjectName(parentObjects[0].name)
            view?.setParentObjectsList(parentObjects)
            view?.notifyParentDataSetChanged()
        } else {
            view?.hideParentObjectView()
        }
    }

    private fun obtainParentObjects(parentId: Long) {
        val parentObject = projectObjectsInteractor.getProjectObject(parentId)
        parentObjects.add(parentObject)

        if(parentObject.parentObjectId != null)
            obtainParentObjects(parentObject.parentObjectId!!)
    }

}
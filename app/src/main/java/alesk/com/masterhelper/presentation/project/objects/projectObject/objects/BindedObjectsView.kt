package alesk.com.masterhelper.presentation.project.objects.projectObject.objects

import alesk.com.masterhelper.data.entities.ProjectObject
import alesk.com.masterhelper.presentation.models.ObjectModel

interface BindedObjectsView {
    fun getObject(): ObjectModel
    fun setParentObjectName(name: String)
    fun hideParentObjectView()
    fun showParentObjectView()
    fun setChildObjectsList(items: List<ProjectObject>)
    fun setParentObjectsList(items: List<ProjectObject>)
    fun showCreateObjectDialog(onCreate: (String) -> Unit)
    fun showChangeParentObjectDialog(objects: List<ProjectObject>)
    fun notifyChildDataSetChanged()
    fun notifyParentDataSetChanged()
    fun askForDeletingChildObject(projectObject: ProjectObject)
    fun hideChangeParentObjectDialog()
}
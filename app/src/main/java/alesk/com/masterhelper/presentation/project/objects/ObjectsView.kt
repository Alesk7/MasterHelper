package alesk.com.masterhelper.presentation.project.objects

import alesk.com.masterhelper.presentation.models.ObjectModel

interface ObjectsView {
    fun showAddObjectDialog(objectsList: List<ObjectModel>, onOk: (String, ObjectModel) -> Unit)
    fun getProjectId(): Long
    fun setObjectsList(objects: List<ObjectModel>)
    fun updateViewBindings()
    fun getNoParentObjectString(): String
}
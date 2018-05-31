package alesk.com.masterhelper.presentation.project.objects.projectObject.materials

import alesk.com.masterhelper.data.entities.Material

interface ObjectMaterialsView {
    fun setMaterialsList(items: List<Material>)
    fun getObjectId(): Long
    fun getProjectId(): Long
    fun showAddMaterialBindingDialog(materialList: List<Material>)
    fun showCreateMaterialDialog(onCreate: (String, Double?, String) -> Unit)
    fun notifyDataSetChanged()
    fun hideAddMaterialBindingDialog()
}
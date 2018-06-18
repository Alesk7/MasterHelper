package alesk.com.masterhelper.presentation.project.materials

import alesk.com.masterhelper.data.entities.Material

interface MaterialsView {
    fun showAddMaterialDialog(onOk: (String, Double?, String) -> Unit)
    fun notifyItemRemoved(pos: Int)
    fun updateViewBindings()
    fun getProjectId(): Long
    fun setMaterialsList(materials: List<Material>)
    fun askForDeleting(material: Material, position: Int)
}
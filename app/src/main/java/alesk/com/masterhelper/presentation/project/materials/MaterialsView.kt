package alesk.com.masterhelper.presentation.project.materials

import alesk.com.masterhelper.data.entities.Material

interface MaterialsView {
    fun showAddMaterialDialog(onOk: (String, Double?, String) -> Unit)
    fun showEditMaterialDialog(name: String, quantity: Double, unit: String,
                          onOk: (String, Double?, String) -> Unit)
    fun notifyItemChanged(pos: Int)
    fun updateViewBindings()
    fun getProjectId(): Long
    fun setMaterialsList(materials: List<Material>)
}
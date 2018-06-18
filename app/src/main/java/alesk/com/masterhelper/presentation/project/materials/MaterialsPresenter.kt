package alesk.com.masterhelper.presentation.project.materials

import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.domain.interactor.MaterialsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import javax.inject.Inject

class MaterialsPresenter @Inject constructor(
        private val materialsInteractor: MaterialsInteractor
): BasePresenter<MaterialsView, MaterialsRouter>() {

    lateinit var materialsList: List<Material>

    override fun onStart() {
        updateViewMaterialsList()
    }

    fun onAddMaterial(){
        view?.showAddMaterialDialog { name, quantity, unit ->
            if(name.isBlank()) return@showAddMaterialDialog
            val material = Material(name, quantity ?: 1.0, 0.0, unit, 0.0, false)
            material.projectId = view!!.getProjectId()
            materialsInteractor.addMaterial(material)
            updateViewMaterialsList()
            view?.updateViewBindings()
        }
    }

    fun onDeleteMaterial(material: Material, position: Int){
        view?.askForDeleting(material, position)
    }

    fun deleteMaterial(material: Material, position: Int){
        materialsInteractor.deleteMaterial(material)
        updateViewMaterialsList()
        view?.notifyItemRemoved(position)
    }

    fun onMaterialStatusChanged(isChecked: Boolean, material: Material){
        material.isPurchased = isChecked
        materialsInteractor.editMaterial(material)
    }

    private fun updateViewMaterialsList() {
        materialsList = materialsInteractor.getMaterialsByProjectId(view!!.getProjectId())
        view?.setMaterialsList(materialsList)
    }

}
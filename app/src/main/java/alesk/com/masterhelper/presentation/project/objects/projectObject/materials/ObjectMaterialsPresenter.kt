package alesk.com.masterhelper.presentation.project.objects.projectObject.materials

import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.domain.interactor.MaterialsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectRouter
import javax.inject.Inject

class ObjectMaterialsPresenter @Inject constructor(
        val materialsInteractor: MaterialsInteractor
): BasePresenter<ObjectMaterialsView, ObjectRouter>() {

    lateinit var materialsList: List<Material>
    var objectId: Long = 0

    override fun onStart() {
        objectId = view!!.getObjectId()
        updateViewMaterialList()
    }

    fun updateViewMaterialList(){
        materialsList = materialsInteractor.getMaterialsByObjectId(objectId)
        view?.setMaterialsList(materialsList)
    }

    fun onAddMaterialBinding(){
        val availableMaterials = materialsInteractor
                .getMaterialsByProjectId(view!!.getProjectId()).filter { it.objectId != objectId }
        view?.showAddMaterialBindingDialog(availableMaterials)
    }

    fun onMaterialClicked(material: Material) {
    }

    fun onAddMaterialClicked(material: Material){
        material.objectId = objectId
        materialsInteractor.editMaterial(material)
        updateViewMaterialList()
        view?.notifyDataSetChanged()
        view?.hideAddMaterialBindingDialog()
    }

    fun onCreateMaterialClicked(){
        view?.showCreateMaterialDialog({ name, quantity, unit ->
            val material = Material(name = name, quantity = quantity ?: 0.0, unit = unit)
            material.projectId = view!!.getProjectId()
            material.objectId = objectId
            materialsInteractor.addMaterial(material)
            updateViewMaterialList()
            view?.notifyDataSetChanged()
            view?.hideAddMaterialBindingDialog()
        })
    }

}
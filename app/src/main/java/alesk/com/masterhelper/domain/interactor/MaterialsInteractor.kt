package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.domain.repository.MaterialRepository
import javax.inject.Inject

class MaterialsInteractor @Inject constructor(val materialRepository: MaterialRepository) {

    fun getMaterialsByProjectId(projectId: Long): List<Material> {
        return materialRepository.getMaterialsByProjectId(projectId).asReversed()
    }

    fun addMaterial(material: Material){
        materialRepository.addMaterial(material)
    }

    fun editMaterial(material: Material){
        materialRepository.editMaterial(material)
    }

}
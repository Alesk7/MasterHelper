package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.dao.MaterialDAO
import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.domain.repository.MaterialRepository
import javax.inject.Inject

class MaterialRepositoryImpl @Inject constructor(
        val materialDAO: MaterialDAO
): MaterialRepository {

    override fun getMaterial(id: Long): Material? {
        return materialDAO.getMaterial(id)
    }

    override fun getMaterialsByProjectId(projectId: Long): List<Material> {
        return materialDAO.getMaterialsByProjectId(projectId)
    }

    override fun addMaterial(material: Material) {
        materialDAO.addMaterial(material)
    }

    override fun editMaterial(material: Material) {
        materialDAO.editMaterial(material)
    }

}
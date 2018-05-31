package alesk.com.masterhelper.domain.repository

import alesk.com.masterhelper.data.entities.Material

interface MaterialRepository {
    fun getMaterial(id: Long): Material?
    fun getMaterialsByProjectId(projectId: Long): List<Material>
    fun getMaterialsByObjectId(objectId: Long): List<Material>
    fun getMaterialsByJobId(jobId: Long): List<Material>
    fun addMaterial(material: Material)
    fun editMaterial(material: Material)
}
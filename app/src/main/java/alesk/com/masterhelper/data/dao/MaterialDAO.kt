package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.Material
import android.arch.persistence.room.*

@Dao
interface MaterialDAO {

    @Insert
    fun addMaterial(material: Material)

    @Update
    fun editMaterial(material: Material)

    @Delete
    fun deleteMaterial(material: Material)

    @Query("SELECT * FROM Material WHERE id = :id")
    fun getMaterial(id: Long): Material

    @Query("SELECT * FROM Material WHERE projectId = :projectId")
    fun getMaterialsByProjectId(projectId: Long): List<Material>

    @Query("SELECT * FROM Material WHERE objectId = :objectId")
    fun getMaterialsByObjectId(objectId: Long): List<Material>

    @Query("SELECT * FROM Material WHERE jobId = :jobId")
    fun getMaterialsByJobId(jobId: Long): List<Material>

}
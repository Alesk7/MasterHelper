package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.Material
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface MaterialDAO {

    @Insert
    fun addMaterial(material: Material)

    @Update
    fun editMaterial(material: Material)

    @Query("SELECT * FROM Material WHERE id = :id")
    fun getMaterial(id: Long): Material

    @Query("SELECT * FROM Material WHERE projectId = :projectId")
    fun getMaterialsByProjectId(projectId: Long): List<Material>

}
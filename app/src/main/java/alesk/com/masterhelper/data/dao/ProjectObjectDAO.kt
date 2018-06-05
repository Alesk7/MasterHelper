package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.ProjectObject
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ProjectObjectDAO {

    @Query("DELETE FROM ProjectObject WHERE id = :id")
    fun deleteProjectObject(id: Long)

    @Insert
    fun addProjectObject(obj: ProjectObject)

    @Update
    fun editProjectObject(obj: ProjectObject)

    @Query("SELECT * FROM ProjectObject WHERE id = :id")
    fun getProjectObject(id: Long): ProjectObject

    @Query("SELECT * FROM ProjectObject WHERE projectId = :projectId")
    fun getProjectObjectsByProjectId(projectId: Long): List<ProjectObject>

    @Query("SELECT * FROM ProjectObject WHERE parentObjectId = :parentId")
    fun getProjectObjectsByParentId(parentId: Long): List<ProjectObject>

}
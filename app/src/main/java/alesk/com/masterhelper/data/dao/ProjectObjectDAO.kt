package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.ProjectObject
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ProjectObjectDAO {

    @Insert
    fun addProjectObject(obj: ProjectObject)

    @Update
    fun editProjectObject(obj: ProjectObject)

    @Query("SELECT * FROM ProjectObject WHERE id = :id")
    fun getProjectObject(id: Long): ProjectObject

    @Query("SELECT * FROM ProjectObject WHERE projectId = :projectId")
    fun getProjectObjectsByProjectId(projectId: Long): List<ProjectObject>

}
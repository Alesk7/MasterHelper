package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.Project
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface ProjectDAO {

    @Insert
    fun createProject(project: Project)

    @Update
    fun updateProject(project: Project)

    @Query("SELECT * FROM Project WHERE id = :id")
    fun getProject(id: Long): Project

    @Query("SELECT * FROM Project")
    fun getAllProjects(): List<Project>

    @Query("SELECT * FROM Project WHERE isComplete = 1")
    fun getCompletedProjects(): List<Project>

    @Query("SELECT * FROM Project WHERE isComplete = 0")
    fun getNotCompletedProjects(): List<Project>

    @Query("DELETE FROM Project WHERE id = :id")
    fun deleteProject(id: Long)

}
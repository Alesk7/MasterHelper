package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.Job
import android.arch.persistence.room.*

@Dao
interface JobDAO {

    @Insert
    fun addJob(job: Job)

    @Update
    fun editJob(job: Job)

    @Delete
    fun deleteJob(job: Job)

    @Query("SELECT * FROM Job WHERE id = :id")
    fun getJob(id: Long): Job

    @Query("SELECT * FROM Job WHERE projectId = :projectId")
    fun getJobsByProjectId(projectId: Long): List<Job>

    @Query("SELECT * FROM Job WHERE objectId = :objectId")
    fun getJobsByObjectId(objectId: Long): List<Job>

}
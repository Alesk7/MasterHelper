package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.Job
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface JobDAO {

    @Insert
    fun addJob(job: Job)

    @Update
    fun editJob(job: Job)

    @Query("SELECT * FROM Job WHERE id = :id")
    fun getJob(id: Long): Job

    @Query("SELECT * FROM Job WHERE projectId = :projectId")
    fun getJobsByProjectId(projectId: Long): List<Job>

}
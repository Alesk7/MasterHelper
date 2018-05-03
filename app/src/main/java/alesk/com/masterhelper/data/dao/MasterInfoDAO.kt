package alesk.com.masterhelper.data.dao

import alesk.com.masterhelper.data.entities.MasterInfo
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface MasterInfoDAO {

    @Insert
    fun saveMasterInfo(masterInfo: MasterInfo)

    @Update
    fun updateMasterInfo(masterInfo: MasterInfo)

    @Query("SELECT * FROM MasterInfo")
    fun getMasterInfo(): MasterInfo

}
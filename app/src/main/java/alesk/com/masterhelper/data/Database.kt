package alesk.com.masterhelper.data

import alesk.com.masterhelper.data.dao.JobDAO
import alesk.com.masterhelper.data.dao.MasterInfoDAO
import alesk.com.masterhelper.data.dao.ProjectDAO
import alesk.com.masterhelper.data.dao.ProjectObjectDAO
import alesk.com.masterhelper.data.entities.*
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(MasterInfo::class,
                             Project::class,
                             Client::class,
                             Contract::class,
                             Job::class,
                             ProjectObject::class), version = 4)
abstract class Database: RoomDatabase() {
    abstract fun masterInfoDAO(): MasterInfoDAO
    abstract fun projectDAO(): ProjectDAO
    abstract fun jobDAO(): JobDAO
    abstract fun projectObjectDAO(): ProjectObjectDAO
}
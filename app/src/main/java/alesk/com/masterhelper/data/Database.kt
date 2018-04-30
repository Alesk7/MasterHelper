package alesk.com.masterhelper.data

import alesk.com.masterhelper.data.dao.MasterInfoDAO
import alesk.com.masterhelper.data.dao.ProjectDAO
import alesk.com.masterhelper.data.entities.Client
import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.data.entities.Project
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(MasterInfo::class, Project::class, Client::class), version = 1)
abstract class Database: RoomDatabase() {
    abstract fun masterInfoDAO(): MasterInfoDAO
    abstract fun projectDAO(): ProjectDAO
}
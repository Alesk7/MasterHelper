package alesk.com.masterhelper.data

import alesk.com.masterhelper.data.dao.*
import alesk.com.masterhelper.data.entities.*
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [MasterInfo::class,
                      Project::class,
                      Client::class,
                      Contract::class,
                      Job::class,
                      Material::class,
                      ProjectObject::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun masterInfoDAO(): MasterInfoDAO
    abstract fun projectDAO(): ProjectDAO
    abstract fun jobDAO(): JobDAO
    abstract fun materialDAO(): MaterialDAO
    abstract fun projectObjectDAO(): ProjectObjectDAO
}
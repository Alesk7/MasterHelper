package alesk.com.masterhelper.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

class Migration: Migration(3, 4) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE ProjectObject")
        database.execSQL("CREATE TABLE IF NOT EXISTS ProjectObject" +
                "(name TEXT NOT NULL, projectId INTEGER NOT NULL, parentObjectId INTEGER," +
                " id INTEGER NOT NULL, " +
                "PRIMARY KEY(id), " +
                "FOREIGN KEY(projectId) REFERENCES Project(id) ON DELETE CASCADE, " +
                "FOREIGN KEY(parentObjectId) REFERENCES ProjectObject(id) ON DELETE CASCADE);")
    }

}
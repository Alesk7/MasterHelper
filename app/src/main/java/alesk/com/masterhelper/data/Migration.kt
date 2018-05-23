package alesk.com.masterhelper.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

class Migration: Migration(4, 5) {

    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS Material" +
                "(name TEXT NOT NULL, projectId INTEGER NOT NULL, id INTEGER NOT NULL, " +
                "quantity REAL NOT NULL, unit TEXT NOT NULL, unitPrice REAL NOT NULL, " +
                "priceSum REAL NOT NULL, isPurchased INTEGER NOT NULL, " +
                "PRIMARY KEY(id), " +
                "FOREIGN KEY(projectId) REFERENCES Project(id) ON DELETE CASCADE);")
    }

}
package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.application.injection.modules.ApplicationModule
import alesk.com.masterhelper.data.Database
import alesk.com.masterhelper.data.Migration
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides

@Module(includes = [ApplicationModule::class])
class DatabaseModule {

    @Provides
    fun provideDatabase(): Database{
        return Room
                .databaseBuilder(applicationComponent.getContext(), Database::class.java, "db")
                .addMigrations(Migration())
                .allowMainThreadQueries()
                .build()
    }

}
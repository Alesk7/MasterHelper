package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.Database
import alesk.com.masterhelper.data.dao.*
import dagger.Module
import dagger.Provides

@Module(includes = [DatabaseModule::class])
class DAOModule {

    @Provides
    fun provideMasterInfoDAO(database: Database): MasterInfoDAO {
        return database.masterInfoDAO()
    }

    @Provides
    fun provideProjectDAO(database: Database): ProjectDAO {
        return database.projectDAO()
    }

    @Provides
    fun provideJobDAO(database: Database): JobDAO {
        return database.jobDAO()
    }

    @Provides
    fun provideMaterialDAO(database: Database): MaterialDAO {
        return database.materialDAO()
    }

    @Provides
    fun provideProjectObjectDAO(database: Database): ProjectObjectDAO {
        return database.projectObjectDAO()
    }

}
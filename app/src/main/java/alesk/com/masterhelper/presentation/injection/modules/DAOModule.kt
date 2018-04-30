package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.Database
import alesk.com.masterhelper.data.dao.MasterInfoDAO
import alesk.com.masterhelper.data.dao.ProjectDAO
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

}
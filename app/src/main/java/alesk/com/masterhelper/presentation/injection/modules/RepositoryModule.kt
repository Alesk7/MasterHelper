package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.dao.MasterInfoDAO
import alesk.com.masterhelper.data.dao.ProjectDAO
import alesk.com.masterhelper.data.repository.MasterInfoRepositoryImpl
import alesk.com.masterhelper.data.repository.ProjectsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module(includes = [DAOModule::class])
class RepositoryModule {

    @Provides
    fun provideMasterInfoRepository(masterInfoDAO: MasterInfoDAO): MasterInfoRepositoryImpl{
        return MasterInfoRepositoryImpl(masterInfoDAO)
    }

    @Provides
    fun provideProjectsRepository(projectDAO: ProjectDAO): ProjectsRepositoryImpl{
        return ProjectsRepositoryImpl(projectDAO)
    }

}
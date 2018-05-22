package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.dao.JobDAO
import alesk.com.masterhelper.data.dao.MasterInfoDAO
import alesk.com.masterhelper.data.dao.ProjectDAO
import alesk.com.masterhelper.data.dao.ProjectObjectDAO
import alesk.com.masterhelper.data.repository.JobRepositoryImpl
import alesk.com.masterhelper.data.repository.MasterInfoRepositoryImpl
import alesk.com.masterhelper.data.repository.ProjectObjectRepositoryImpl
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

    @Provides
    fun provideJobRepository(jobDAO: JobDAO): JobRepositoryImpl {
        return JobRepositoryImpl(jobDAO)
    }

    @Provides
    fun provideProjectObjectRepository(projectObjectDAO: ProjectObjectDAO): ProjectObjectRepositoryImpl {
        return ProjectObjectRepositoryImpl(projectObjectDAO)
    }

}
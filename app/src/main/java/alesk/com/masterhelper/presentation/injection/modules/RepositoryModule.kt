package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.dao.*
import alesk.com.masterhelper.data.repository.*
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
    fun provideMaterialRepository(materialDAO: MaterialDAO): MaterialRepositoryImpl {
        return MaterialRepositoryImpl(materialDAO)
    }

    @Provides
    fun provideProjectObjectRepository(projectObjectDAO: ProjectObjectDAO): ProjectObjectRepositoryImpl {
        return ProjectObjectRepositoryImpl(projectObjectDAO)
    }

}
package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.repository.*
import alesk.com.masterhelper.domain.interactor.*
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class InteractorModule {

    @Provides
    fun provideMasterInfoInteractor(masterInfoRepository: MasterInfoRepositoryImpl): MasterInfoInteractor {
        return MasterInfoInteractor(masterInfoRepository)
    }

    @Provides
    fun provideProjectsInteractor(projectsRepository: ProjectsRepositoryImpl): ProjectsInteractor {
        return ProjectsInteractor(projectsRepository)
    }

    @Provides
    fun provideJobsInteractor(jobRepository: JobRepositoryImpl): JobsInteractor {
        return JobsInteractor(jobRepository)
    }

    @Provides
    fun provideMaterialsInteractor(materialRepository: MaterialRepositoryImpl): MaterialsInteractor {
        return MaterialsInteractor(materialRepository)
    }

    @Provides
    fun provideProjectObjectsInteractor(projectObjectRepository: ProjectObjectRepositoryImpl)
            : ProjectObjectsInteractor {
        return ProjectObjectsInteractor(projectObjectRepository)
    }

}
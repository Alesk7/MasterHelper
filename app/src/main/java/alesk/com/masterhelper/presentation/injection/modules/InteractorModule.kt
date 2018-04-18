package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.repository.MasterInfoRepositoryImpl
import alesk.com.masterhelper.data.repository.ProjectsRepositoryImpl
import alesk.com.masterhelper.domain.interactor.MasterInfoInteractor
import alesk.com.masterhelper.domain.interactor.ProjectsInteractor
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

}
package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.repository.MasterInfoRepositoryImpl
import alesk.com.masterhelper.data.repository.ProjectsRepositoryImpl
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module(includes = [RealmModule::class])
class RepositoryModule {

    @Provides
    fun provideMasterInfoRepository(realm: Realm): MasterInfoRepositoryImpl{
        return MasterInfoRepositoryImpl(realm)
    }

    fun provideProjectsRepository(realm: Realm): ProjectsRepositoryImpl{
        return ProjectsRepositoryImpl(realm)
    }

}
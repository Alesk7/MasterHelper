package alesk.com.masterhelper.presentation.injection.modules

import alesk.com.masterhelper.data.repository.MasterInfoRepositoryImpl
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module(includes = [RealmModule::class])
class RepositoryModule {

    @Provides
    fun provideMasterInfoRepository(realm: Realm): MasterInfoRepositoryImpl{
        return MasterInfoRepositoryImpl(realm)
    }

}
package alesk.com.masterhelper.presentation.injection.modules

import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class RealmModule {

    @Provides
    fun provideDefaultRealm(): Realm = Realm.getDefaultInstance()

}
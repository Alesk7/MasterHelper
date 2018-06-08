package alesk.com.masterhelper.application.injection.modules

import android.content.Context
import android.content.res.AssetManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class AssetsModule {

    @Provides
    @Singleton
    fun provideAssets(context: Context): AssetManager {
        return context.assets
    }

}
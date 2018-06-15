package alesk.com.masterhelper.application.injection

import alesk.com.masterhelper.application.injection.modules.AssetsModule
import alesk.com.masterhelper.application.injection.modules.SharedPreferencesModule
import alesk.com.masterhelper.application.utils.SharedPreferencesUtils
import android.content.Context
import android.content.res.AssetManager
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class, AssetsModule::class])
interface ApplicationComponent {
    fun getSharedPreferencesHelper(): SharedPreferencesUtils
    fun getContext(): Context
    fun getAssets(): AssetManager
}
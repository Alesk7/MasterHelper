package alesk.com.masterhelper.application.injection

import alesk.com.masterhelper.application.injection.modules.AssetsModule
import alesk.com.masterhelper.application.injection.modules.DateModule
import alesk.com.masterhelper.application.injection.modules.SharedPreferencesModule
import alesk.com.masterhelper.application.utils.SharedPreferencesHelper
import android.content.Context
import android.content.res.AssetManager
import dagger.Component
import java.text.SimpleDateFormat
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class, AssetsModule::class, DateModule::class])
interface ApplicationComponent {
    fun getSharedPreferencesHelper(): SharedPreferencesHelper
    fun getContext(): Context
    fun getAssets(): AssetManager
    fun getSimpleDateFormat(): SimpleDateFormat
}
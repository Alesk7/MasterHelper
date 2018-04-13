package alesk.com.masterhelper.application.injection

import alesk.com.masterhelper.application.SharedPreferencesHelper
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedPreferencesModule::class])
interface ApplicationComponent {
    fun getSharedPreferencesHelper(): SharedPreferencesHelper
}
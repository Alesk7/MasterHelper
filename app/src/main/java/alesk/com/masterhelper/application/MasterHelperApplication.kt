package alesk.com.masterhelper.application

import alesk.com.masterhelper.application.injection.ApplicationComponent
import alesk.com.masterhelper.application.injection.DaggerApplicationComponent
import alesk.com.masterhelper.application.injection.SharedPreferencesModule
import android.app.Application

lateinit var applicationComponent: ApplicationComponent

class MasterHelperApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .sharedPreferencesModule(SharedPreferencesModule(applicationContext))
                .build()
    }

}
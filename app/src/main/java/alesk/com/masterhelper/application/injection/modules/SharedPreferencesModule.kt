package alesk.com.masterhelper.application.injection.modules

import alesk.com.masterhelper.R
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApplicationModule::class])
class SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context
                .getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE)
    }

}
package alesk.com.masterhelper.application.injection

import alesk.com.masterhelper.R
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
class SharedPreferencesModule(val context: Context) {

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(context
                .getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE)
    }

}
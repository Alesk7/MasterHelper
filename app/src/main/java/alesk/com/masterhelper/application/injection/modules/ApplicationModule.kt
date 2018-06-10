package alesk.com.masterhelper.application.injection.modules

import android.app.Application
import android.content.Context
import android.os.Environment
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    fun provideDocumentsPath(): String {
        return "${Environment.getExternalStorageDirectory()}${File.separator}${Environment.DIRECTORY_DOCUMENTS}${File.separator}"
    }

}
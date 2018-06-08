package alesk.com.masterhelper.application.injection.modules

import android.annotation.SuppressLint
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Module
class DateModule {

    @Provides
    fun provideSimpleDateFormat(): SimpleDateFormat{
        return SimpleDateFormat("dd/MM/yyyy")
    }

}
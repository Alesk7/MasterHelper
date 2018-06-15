package alesk.com.masterhelper.application.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesUtils @Inject constructor(val sPref: SharedPreferences) {

    fun putString(key: String, value: String){
        with(sPref.edit()){
            putString(key, value)
            apply()
        }
    }

    fun putInt(key: String, value: Int){
        with(sPref.edit()){
            putInt(key, value)
            apply()
        }
    }

    fun putBoolean(key: String, value: Boolean){
        with(sPref.edit()){
            putBoolean(key, value)
            apply()
        }
    }

    fun putFloat(key: String, value: Float){
        with(sPref.edit()){
            putFloat(key, value)
            apply()
        }
    }

    fun getString(key: String, deafaultValue: String) = sPref.getString(key, deafaultValue)

    fun getInt(key: String, deafaultValue: Int) = sPref.getInt(key, deafaultValue)

    fun getBoolean(key: String, deafaultValue: Boolean) = sPref.getBoolean(key, deafaultValue)

    fun getFloat(key: String, deafaultValue: Float) = sPref.getFloat(key, deafaultValue)

}
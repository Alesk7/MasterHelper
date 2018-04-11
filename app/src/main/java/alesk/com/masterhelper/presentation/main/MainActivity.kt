package alesk.com.masterhelper.presentation.main

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.welcome.WelcomeActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref = getSharedPreferences(getString(R.string.sharedPreferencesName), Context.MODE_PRIVATE)
        if(isFirstStart()) {
            startWelcomeActivity()
            finish()
        }
    }

    private fun isFirstStart() = sharedPref.getBoolean(getString(R.string.isFirstStartKey), true)

    private fun startWelcomeActivity(){
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}

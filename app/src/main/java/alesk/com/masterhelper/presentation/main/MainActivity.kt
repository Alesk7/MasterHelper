package alesk.com.masterhelper.presentation.main

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.SharedPreferencesHelper
import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.presentation.welcome.WelcomeActivity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainRouter {

    lateinit var sPrefHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sPrefHelper = applicationComponent.getSharedPreferencesHelper()

        if(isFirstStart()) {
            startWelcomeActivity()
            finish()
            sPrefHelper.putBoolean(getString(R.string.isFirstStartKey), false)
        }
    }

    private fun isFirstStart() = sPrefHelper
            .getBoolean(getString(R.string.isFirstStartKey), true)

    private fun startWelcomeActivity(){
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}

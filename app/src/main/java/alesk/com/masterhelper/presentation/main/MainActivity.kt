package alesk.com.masterhelper.presentation.main

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.welcome.Welcome
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, Welcome::class.java))
    }

}

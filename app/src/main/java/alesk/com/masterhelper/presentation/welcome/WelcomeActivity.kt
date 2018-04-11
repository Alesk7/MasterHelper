package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : WelcomeView, AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        welcomeViewPager.adapter = WelcomePagerAdapter(supportFragmentManager)
    }

    override fun nextWelcomePage() {
        welcomeViewPager.currentItem += 1
    }

    override fun onBackPressed() {
        if(welcomeViewPager.currentItem == 0)
            super.onBackPressed()
        welcomeViewPager.currentItem -= 1
    }

}

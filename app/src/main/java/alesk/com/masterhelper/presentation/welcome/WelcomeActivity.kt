package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoRouter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(), WelcomeRouter, MasterInfoRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        welcomeViewPager.adapter = WelcomePagerAdapter(supportFragmentManager)
    }

    override fun nextWelcomePage() {
        welcomeViewPager.currentItem += 1
    }

    override fun hideMasterInfo() {
        finish()
    }

    override fun onBackPressed() {
        if(welcomeViewPager.currentItem == 0)
            finishAffinity()
        welcomeViewPager.currentItem -= 1
    }

}

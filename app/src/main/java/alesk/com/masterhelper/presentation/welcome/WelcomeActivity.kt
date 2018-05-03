package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoFragment
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoRouter
import android.os.Bundle

class WelcomeActivity : BaseActivity(), MasterInfoRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        showMasterInfo()
    }

    fun showMasterInfo(){
        supportFragmentManager.inTransaction(false) {
            replace(R.id.container, MasterInfoFragment())
        }
    }

    override fun hideMasterInfo() {
        finish()
    }

}

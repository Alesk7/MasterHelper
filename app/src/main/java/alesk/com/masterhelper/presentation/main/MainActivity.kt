package alesk.com.masterhelper.presentation.main

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.SharedPreferencesHelper
import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.main.newProject.NewProjectFragment
import alesk.com.masterhelper.presentation.main.projects.ProjectsFragment
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoFragment
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoRouter
import alesk.com.masterhelper.presentation.project.ProjectActivity
import alesk.com.masterhelper.presentation.welcome.WelcomeActivity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainRouter, MasterInfoRouter {

    lateinit var sPrefHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
        showProjectsList()
        sPrefHelper = applicationComponent.getSharedPreferencesHelper()

        if(isFirstStart()) {
            startActivity(Intent(this, WelcomeActivity::class.java))
            sPrefHelper.putBoolean(getString(R.string.isFirstStartKey), false)
        }
    }

    private fun initBottomNavigation(){
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.projects_item -> showProjectsList()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun showCreateNewProject() {
        supportFragmentManager.inTransaction(true) {
            replace(R.id.frameLayout, NewProjectFragment())
        }
    }

    override fun showProjectsList() {
        supportFragmentManager.inTransaction(false) {
            replace(R.id.frameLayout, ProjectsFragment())
        }
    }

    override fun showProjectInfo(id: Long) {
        val intent = Intent(this, ProjectActivity::class.java)
        intent.putExtra(getString(R.string.keyIdProject), id)
        startActivity(intent)
    }

    override fun showMasterInfo() {
        supportFragmentManager.inTransaction(true) {
            replace(R.id.frameLayout, MasterInfoFragment())
        }
    }

    override fun hideMasterInfo() {
        supportFragmentManager.popBackStack()
    }

    private fun isFirstStart() = sPrefHelper
            .getBoolean(getString(R.string.isFirstStartKey), true)

}

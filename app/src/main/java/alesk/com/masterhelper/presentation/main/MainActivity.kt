package alesk.com.masterhelper.presentation.main

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.application.utils.SharedPreferencesUtils
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.main.help.HelpActivity
import alesk.com.masterhelper.presentation.main.newProject.NewProjectFragment
import alesk.com.masterhelper.presentation.main.projects.ProjectsFragment
import alesk.com.masterhelper.presentation.main.projects.completedProjects.CompletedProjectsFragment
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoFragment
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoRouter
import alesk.com.masterhelper.presentation.project.ProjectActivity
import alesk.com.masterhelper.presentation.welcome.WelcomeActivity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainRouter, MasterInfoRouter {

    lateinit var sPrefUtils: SharedPreferencesUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
        showProjectsList()
        sPrefUtils = applicationComponent.getSharedPreferencesHelper()

        if(isFirstStart()) {
            startActivity(Intent(this, WelcomeActivity::class.java))
            sPrefUtils.putBoolean(getString(R.string.isFirstStartKey), false)
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

    fun updateStatusBarColor(color: Int){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

    fun updateActionBarColor(color: Int){
        supportActionBar?.setBackgroundDrawable(ColorDrawable(color))
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

    override fun showCompletedProjectsList() {
        supportFragmentManager.inTransaction(true) {
            replace(R.id.frameLayout, CompletedProjectsFragment())
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

    override fun showHelp() {
        startActivity(Intent(this, HelpActivity::class.java))
    }

    override fun hideMasterInfo() {
        supportFragmentManager.popBackStack()
    }

    private fun isFirstStart() = sPrefUtils
            .getBoolean(getString(R.string.isFirstStartKey), true)

}

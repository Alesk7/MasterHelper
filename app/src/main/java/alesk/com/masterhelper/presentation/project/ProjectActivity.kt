package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.common.BaseActivity
import android.os.Bundle
import android.view.Menu
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : BaseActivity(), ProjectRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
        viewPager.adapter = ProjectPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = getString(R.string.projectInfo)
        tabLayout.getTabAt(1)?.text = getString(R.string.projectObjects)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.project_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}

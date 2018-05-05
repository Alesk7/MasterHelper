package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.project.clientInfo.ClientInfoActivity
import alesk.com.masterhelper.presentation.project.contractDetails.ContractActivity
import android.content.Intent
import android.os.Bundle
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

    override fun close() {
        finish()
    }

    override fun showClientInfo(projectModel: ProjectModel) {
        val intent = Intent(this, ClientInfoActivity::class.java)
        intent.putExtra(getString(R.string.keyProjectModel), projectModel)
        startActivity(intent)
    }

    override fun showContractDetails(projectModel: ProjectModel) {
        val intent = Intent(this, ContractActivity::class.java)
        intent.putExtra(getString(R.string.keyProjectModel), projectModel)
        startActivity(intent)
    }

}

package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.showAskingDialog
import alesk.com.masterhelper.application.utils.showEditDialog
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.project.clientInfo.ClientInfoActivity
import alesk.com.masterhelper.presentation.project.contractDetails.ContractActivity
import alesk.com.masterhelper.presentation.project.jobs.JobsActivity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_project.*
import javax.inject.Inject

class ProjectActivity : BaseActivity(), ProjectView, ProjectRouter {

    @Inject
    lateinit var presenter: ProjectPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
        inject()
        initPresenter()
        viewPager.adapter = ProjectPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = getString(R.string.projectInfo)
        tabLayout.getTabAt(1)?.text = getString(R.string.projectObjects)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    private fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    private fun initPresenter(){
        presenter.view = this
        presenter.router = this
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.project_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.edit_item -> presenter.onEditProjectName()
            R.id.delete_item -> askForDeleting()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun close() {
        finish()
    }

    override fun showClientInfo(projectModel: ProjectModel) {
        val intent = Intent(this, ClientInfoActivity::class.java)
        intent.putExtra(getString(R.string.keyProjectModel), projectModel)
        startActivity(intent)
    }

    override fun showJobs(projectId: Long) {
        val intent = Intent(this, JobsActivity::class.java)
        intent.putExtra(getString(R.string.keyIdProject), projectId)
        startActivity(intent)
    }

    override fun showContractDetails(projectModel: ProjectModel) {
        val intent = Intent(this, ContractActivity::class.java)
        intent.putExtra(getString(R.string.keyProjectModel), projectModel)
        startActivity(intent)
    }

    override fun getProjectId() = intent.getLongExtra(getString(R.string.keyIdProject), 0)

    override fun getProjectNameString() = getString(R.string.projectName)

    override fun setProjectName(name: String) {
        supportActionBar?.title = name
    }

    override fun showEditDialog(title: String, body: String, onSave: (String) -> Unit) {
        showEditDialog(this, title, body, onSave)
    }

    override fun askForDeleting() {
        showAskingDialog(this,
                         getString(R.string.sureForDeletingProject),
                         getString(R.string.delete),
                         { presenter.onDeleteProject() })
    }

}

package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.showAskingDialog
import alesk.com.masterhelper.application.utils.showEditDialog
import alesk.com.masterhelper.databinding.ActivityProjectBinding
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.project.clientInfo.ClientInfoActivity
import alesk.com.masterhelper.presentation.project.contractDetails.ContractActivity
import alesk.com.masterhelper.presentation.project.jobs.JobsActivity
import alesk.com.masterhelper.presentation.project.materials.MaterialsActivity
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectActivity
import alesk.com.masterhelper.presentation.project.prices.PricesActivity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_project.*
import java.io.Serializable
import javax.inject.Inject
import kotlin.reflect.KClass

class ProjectActivity : BaseActivity(), ProjectView, ProjectRouter {

    @Inject
    lateinit var presenter: ProjectPresenter
    lateinit var binding: ActivityProjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project)
        setSupportActionBar(toolbar)
        inject()
        initPresenter()
        viewPager.adapter = ProjectPagerAdapter(supportFragmentManager)
        viewPager.pageMargin = resources.getDimension(R.dimen.viewPagerMargin).toInt()
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = getString(R.string.projectInfo)
        tabLayout.getTabAt(1)?.text = getString(R.string.projectObjects)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
    }

    override fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    override fun initPresenter(){
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
        startNewActivity(ClientInfoActivity::class, getString(R.string.keyProjectModel), projectModel)
    }

    override fun showJobs(projectId: Long) {
        startNewActivity(JobsActivity::class, getString(R.string.keyIdProject), projectId)
    }

    override fun showMaterials(projectId: Long) {
        startNewActivity(MaterialsActivity::class, getString(R.string.keyIdProject), projectId)
    }

    override fun showContractDetails(projectModel: ProjectModel) {
        startNewActivity(ContractActivity::class, getString(R.string.keyProjectModel), projectModel)
    }

    override fun showPrices(projectId: Long) {
        startNewActivity(PricesActivity::class, getString(R.string.keyIdProject), projectId)
    }

    override fun showObject(objectModel: ObjectModel) {
        startNewActivity(ObjectActivity::class, getString(R.string.keyObjectModel), objectModel)
    }

    private fun startNewActivity(activity: KClass<*>, extraKey: String, extraValue: Long){
        val intent = Intent(this, activity.java)
        intent.putExtra(extraKey, extraValue)
        startActivity(intent)
    }

    private fun startNewActivity(activity: KClass<*>, extraKey: String, extraValue: Serializable){
        val intent = Intent(this, activity.java)
        intent.putExtra(extraKey, extraValue)
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

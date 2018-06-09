package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.providers.DocsFileProvider
import alesk.com.masterhelper.application.utils.buildCustomViewDialogWithoutButtons
import alesk.com.masterhelper.application.utils.showAskingDialog
import alesk.com.masterhelper.application.utils.showMessageDialog
import alesk.com.masterhelper.application.utils.showTextFieldDialog
import alesk.com.masterhelper.databinding.ActivityProjectBinding
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.project.clientInfo.ClientInfoActivity
import alesk.com.masterhelper.presentation.project.contract.ContractActivity
import alesk.com.masterhelper.presentation.project.jobs.JobsActivity
import alesk.com.masterhelper.presentation.project.materials.MaterialsActivity
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectActivity
import alesk.com.masterhelper.presentation.project.prices.PricesActivity
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.FileProvider
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_project.*
import kotlinx.android.synthetic.main.dialog_print.view.*
import java.io.File
import java.io.Serializable
import javax.inject.Inject
import kotlin.reflect.KClass

@Suppress("DEPRECATION")
class ProjectActivity : BaseActivity(), ProjectView, ProjectRouter {

    @Inject
    lateinit var presenter: ProjectPresenter
    lateinit var binding: ActivityProjectBinding
    private lateinit var progressDialog: ProgressDialog

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
            R.id.print_item -> showPrintDialog()
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
        showTextFieldDialog(this, title, body, onSave)
    }

    override fun askForDeleting() {
        showAskingDialog(this,
                         getString(R.string.sureForDeletingProject),
                         getString(R.string.delete),
                         { presenter.onDeleteProject() })
    }

    override fun showPrintDialog() {
        val view = initPrintDialogView()
        val dialog = buildCustomViewDialogWithoutButtons(this, view, getString(R.string.print)).show()
        view.closeButton.setOnClickListener { dialog.cancel() }
    }

    @SuppressLint("InflateParams")
    private fun initPrintDialogView(): View {
        val view = layoutInflater.inflate(R.layout.dialog_print, null)
        view.estimateButton.setOnClickListener{ presenter.printEstimate() }
        view.contractButton.setOnClickListener{  }
        view.actButton.setOnClickListener{  }
        return view
    }

    override fun showProgressBar() {
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle(getString(R.string.loading))
        progressDialog.setMessage(getString(R.string.pleaseWait))
        progressDialog.show()
    }

    override fun hideProgressBar() {
        progressDialog.hide()
    }

    override fun showDocumentGeneratedDialog(generatedFilePath: String) {
        showMessageDialog(this, getString(R.string.done),
                            String.format(getString(R.string.fileSaved), generatedFilePath),
                            getString(R.string.done),
                            getString(R.string.open),
                            { tryOpenDocFile(generatedFilePath) })
    }

    fun tryOpenDocFile(filePath: String) {
        val uri = FileProvider.getUriForFile(this, DocsFileProvider::class.java.name, File(filePath))
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/msword")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

}

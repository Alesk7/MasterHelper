package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.providers.DocsFileProvider
import alesk.com.masterhelper.application.utils.*
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
import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.FileProvider
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
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
    lateinit var menu: Menu
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_project)
        setSupportActionBar(toolbar)
        inject()
        initPresenter()
        viewPager.adapter = ProjectPagerAdapter(supportFragmentManager)
        viewPager.pageMargin = resources.getDimension(R.dimen.viewPagerMargin).toInt()
        initTabLayout()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
    }

    override fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.router = this
    }

    private fun initTabLayout(){
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.text = getString(R.string.projectInfo)
        tabLayout.getTabAt(1)?.text = getString(R.string.projectObjects)
    }

    override fun setArchiveMenuItem() {
        menu.findItem(R.id.archive_item).title = getString(R.string.archiveProject)
    }

    override fun setUnarchiveMenuItem() {
        menu.findItem(R.id.archive_item).title = getString(R.string.unarchiveProject)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu!!
        presenter.checkIfProjectInArchive()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.project_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.edit_item -> presenter.onEditProjectName()
            R.id.delete_item -> presenter.onDeleteProject()
            R.id.print_item -> {
                if(isWriteExternalStoragePermissionGranted()) presenter.onPrintClicked()
                else requestWriteExternalStoragePermission()
            }
            R.id.open_folder_item -> presenter.onOpenFolderClicked()
            R.id.archive_item -> presenter.onSetArchived()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setArchivedColor() {
        setActionAndStatusBarColors(
                resources.getColor(R.color.colorCompletedProjectsPrimary),
                resources.getColor(R.color.colorCompletedProjectsPrimaryDark))
        tabLayout.setBackgroundColor(resources.getColor(R.color.colorCompletedProjectsPrimary))
    }

    override fun setPrimaryColor() {
        setActionAndStatusBarColors(
                resources.getColor(R.color.colorPrimary),
                resources.getColor(R.color.colorPrimaryDark))
        tabLayout.setBackgroundColor(resources.getColor(R.color.colorPrimary))
    }

    private fun setActionAndStatusBarColors(actionBarColor: Int, statusBarColor: Int) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(actionBarColor))
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = statusBarColor
    }

    override fun close() {
        finish()
    }

    private fun requestWriteExternalStoragePermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
    }

    private fun isWriteExternalStoragePermissionGranted(): Boolean {
        val permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return permission == PackageManager.PERMISSION_GRANTED
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
        showAskingDialog(this, getString(R.string.sureForDeletingProject), getString(R.string.delete)) {
            presenter.deleteProject()
        }
    }

    override fun showPrintDialog() {
        val view = initPrintDialogView()
        val dialog = buildCustomViewDialogWithoutButtons(this, view, getString(R.string.print)).show()
        view.closeButton.setOnClickListener { dialog.cancel() }
    }

    @SuppressLint("InflateParams")
    private fun initPrintDialogView(): View {
        val view = layoutInflater.inflate(R.layout.dialog_print, null)
        view.estimateButton.setOnClickListener { presenter.printEstimate() }
        view.contractButton.setOnClickListener { presenter.printContract() }
        view.actButton.setOnClickListener { presenter.printAct() }
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
                            getString(R.string.open))
                            { tryOpenDocFile(generatedFilePath) }
    }

    private fun tryOpenDocFile(filePath: String) {
        val uri = FileProvider.getUriForFile(this, DocsFileProvider::class.java.name, File(filePath))
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/msword")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        if(packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null)
            startActivity(intent)
        else showLongToast(this, getString(R.string.appNotFound))
    }

    override fun openDocFolder() {
        val uri = Uri.parse(DOCUMENTS_PATH)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "*/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

}

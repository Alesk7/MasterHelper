package alesk.com.masterhelper.presentation.project.projectInfo

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.showEditDialog
import alesk.com.masterhelper.databinding.FragmentProjectInfoBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.project.ProjectRouter
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class ProjectInfoFragment: BaseFragment(), ProjectInfoView {

    @Inject
    lateinit var presenter: ProjectInfoPresenter
    lateinit var binding: FragmentProjectInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_info, container, false)
        return binding.root
    }

    override fun showEditDialog(title: String, body: String, onSave: (String) -> Unit) {
        showEditDialog(context!!, title, body, onSave)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.project = presenter.projectModel
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.router = activity as ProjectRouter
    }

    private fun isCallPermissionGranted(): Boolean {
        val permission = ActivityCompat.checkSelfPermission(
                activity as AppCompatActivity, Manifest.permission.CALL_PHONE)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCallPermission() = ActivityCompat.requestPermissions(activity as AppCompatActivity,
            arrayOf(Manifest.permission.CALL_PHONE), 0)

    override fun tryMakeCall(number: String) {
        if(isCallPermissionGranted()) makeCall(number) else requestCallPermission()
    }

    private fun makeCall(number: String){
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    override fun getProjectId(): Long {
        return activity!!.intent!!.getLongExtra(getString(R.string.keyIdProject), 0)
    }

    override fun getProjectDescriptionString() = getString(R.string.projectDescription)

    override fun getProjectAddressString() = getString(R.string.projectAddress)

    override fun updateViewBindings() = binding.invalidateAll()

}
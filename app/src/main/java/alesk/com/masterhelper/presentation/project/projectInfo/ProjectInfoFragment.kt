package alesk.com.masterhelper.presentation.project.projectInfo

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.FragmentProjectInfoBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.project.ProjectRouter
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import android.widget.EditText
import javax.inject.Inject

class ProjectInfoFragment: BaseFragment(), ProjectInfoView {

    @Inject
    lateinit var presenter: ProjectInfoPresenter
    lateinit var binding: FragmentProjectInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_info, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.project_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.edit_item -> presenter.onEditProjectName()
            R.id.delete_item -> askForDeleting()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showEditDialog(title: String, body: String, onSave: (String) -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        val editText = EditText(context)
        editText.setText(body)
        builder.setView(editText)
        builder.setPositiveButton(getString(R.string.save), {
            dialog, i -> onSave(editText.text.toString())
        })
        builder.setNegativeButton(getString(R.string.cancel), { dialog, i -> dialog.cancel() })
        builder.show()
    }

    override fun askForDeleting() {
        val builder = AlertDialog.Builder(context)
        builder
                .setTitle(getString(R.string.sureForDeletingProject))
                .setNegativeButton(getString(R.string.no), { dialog, i -> dialog.cancel() })
                .setPositiveButton(getString(R.string.yes), { dialog, i -> presenter.onDeleteProject() })
                .show()
    }

    override fun setProjectName(name: String) {
        setActionBarTitle(name)
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

    override fun getProjectId(): Long {
        return activity!!.intent!!.getLongExtra(getString(R.string.keyIdProject), 0)
    }

    override fun getProjectNameString() = getString(R.string.projectName)

    override fun getProjectDescriptionString() = getString(R.string.projectDescription)

    override fun getProjectAddressString() = getString(R.string.projectAddress)

    override fun updateViewBindings() = binding.invalidateAll()

}
package alesk.com.masterhelper.presentation.project.objects.projectObject.objects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.buildCustomViewDialogWithoutButtons
import alesk.com.masterhelper.application.utils.showAskingDialog
import alesk.com.masterhelper.application.utils.showTextFieldDialog
import alesk.com.masterhelper.data.entities.ProjectObject
import alesk.com.masterhelper.databinding.FragmentBindedObjectsBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectRouter
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_add_object_binding.view.*
import kotlinx.android.synthetic.main.fragment_binded_objects.*

class BindedObjectsFragment:
        BaseFragment<BindedObjectsPresenter, BindedObjectsView, ObjectRouter>(), BindedObjectsView {

    lateinit var binding: FragmentBindedObjectsBinding
    lateinit var childObjectsAdapter: ChildObjectsAdapter
    lateinit var parentObjectsAdapter: ParentObjectsAdapter
    lateinit var changeParentObjectDialog: AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_binded_objects, container, false)
        childObjectsAdapter = ChildObjectsAdapter(context) { presenter.onDeleteChildObjectClicked(it) }
        parentObjectsAdapter = ParentObjectsAdapter(context) {}
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.childObjectsAdapter = childObjectsAdapter
        binding.parentObjectsAdapter = parentObjectsAdapter
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun askForDeletingChildObject(projectObject: ProjectObject) {
        showAskingDialog(context!!, getString(R.string.sureForDeletingObject), getString(R.string.delete)) {
            presenter.onDeleteChildObject(projectObject)
        }
    }

    override fun showCreateObjectDialog(onCreate: (String) -> Unit) {
        showTextFieldDialog(context!!, getString(R.string.addChildObject), "") { onCreate(it) }
    }

    override fun showChangeParentObjectDialog(objects: List<ProjectObject>) {
        val view = initChangeParentObjectDialogView(objects)
        changeParentObjectDialog = buildCustomViewDialogWithoutButtons(
                context!!, view, getString(R.string.parentObjectIs)).show()
    }

    override fun hideChangeParentObjectDialog() {
        changeParentObjectDialog.cancel()
    }

    @SuppressLint("InflateParams")
    private fun initChangeParentObjectDialogView(objects: List<ProjectObject>): View {
        val view = layoutInflater.inflate(R.layout.dialog_add_object_binding, null)
        val adapter = ParentObjectsAdapter(context) { presenter.onParentObjectSelected(it) }
        adapter.items = objects
        view.bindingsList.adapter = adapter
        view.createButton.visibility = View.GONE
        return view
    }

    override fun setChildObjectsList(items: List<ProjectObject>) {
        childObjectsAdapter.items = items
    }

    override fun setParentObjectsList(items: List<ProjectObject>) {
        parentObjectsAdapter.items = items
    }

    override fun setParentObjectName(name: String) {
        parentObjectName.text = name
    }

    override fun hideParentObjectView() {
        parentObjectName.visibility = View.GONE
    }

    override fun showParentObjectView() {
        parentObjectName.visibility = View.VISIBLE
    }

    override fun getObject() = activity!!.intent
            .getSerializableExtra(getString(R.string.keyObjectModel)) as ObjectModel

    override fun notifyChildDataSetChanged() = childObjectsAdapter.notifyDataSetChanged()

    override fun notifyParentDataSetChanged() = parentObjectsAdapter.notifyDataSetChanged()

}
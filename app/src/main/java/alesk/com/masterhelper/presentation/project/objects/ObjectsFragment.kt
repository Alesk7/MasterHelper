package alesk.com.masterhelper.presentation.project.objects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.showCustomViewDialog
import alesk.com.masterhelper.databinding.FragmentObjectsBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.project.ProjectRouter
import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_add_object.view.*

class ObjectsFragment : BaseFragment<ObjectsPresenter, ObjectsView, ProjectRouter>(), ObjectsView {

    lateinit var binding: FragmentObjectsBinding
    lateinit var adapter: ObjectsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_objects, container, false)
        adapter = ObjectsAdapter(context!!, { presenter.onObjectClicked(it) })
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.adapter = adapter
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun showAddObjectDialog(objectsList: List<ObjectModel>, onOk: (String, ObjectModel) -> Unit) {
        val view = initObjectDialogView(objectsList)
        showCustomViewDialog(context!!, view, getString(R.string.addObject), { di, v ->
            onOk(view.name.text.toString(), view.parentObjectSpinner.selectedItem as ObjectModel)
        }).show()
    }

    @SuppressLint("InflateParams")
    private fun initObjectDialogView(objectsList: List<ObjectModel>): View{
        val view = layoutInflater.inflate(R.layout.dialog_add_object, null)
        val adapter = ParentObjectsSpinnerAdapter(context!!, R.layout.spinner_item, objectsList)
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        view.parentObjectSpinner.adapter = adapter
        return view
    }

    override fun setObjectsList(objects: List<ObjectModel>) {
        adapter.items = objects
    }

    override fun getProjectId(): Long {
        return activity!!.intent!!.getLongExtra(getString(R.string.keyIdProject), 0)
    }

    override fun updateViewBindings() = binding.invalidateAll()

    override fun getNoParentObjectString() = getString(R.string.no)

}

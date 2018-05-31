package alesk.com.masterhelper.presentation.project.objects.projectObject.materials

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.showCustomViewDialog
import alesk.com.masterhelper.application.utils.showCustomViewDialogWithoutButtons
import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectRouter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_add_object_binding.view.*
import kotlinx.android.synthetic.main.dialog_job.view.*
import kotlinx.android.synthetic.main.fragment_object_bindings.view.*

class ObjectMaterialsFragment
    : BaseFragment<ObjectMaterialsPresenter, ObjectMaterialsView, ObjectRouter>(), ObjectMaterialsView {

    lateinit var adapter: ObjectMaterialsAdapter
    lateinit var dialog: android.app.AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_object_bindings, container, false)
        view.addButton.text = getString(R.string.addMaterial)
        view.addButton.setOnClickListener{ presenter.onAddMaterialBinding() }
        adapter = ObjectMaterialsAdapter(context, { presenter.onMaterialClicked(it) })
        view.list.adapter = adapter
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun showCreateMaterialDialog(onCreate: (String, Double?, String) -> Unit) {
        val view = initMaterialDialogView()
        showCustomViewDialog(context!!, view, getString(R.string.addMaterial), { d, i ->
            onCreate(view.name.text.toString(),
                    view.quantity.text.toString().toDoubleOrNull(),
                    view.unit.selectedItem.toString())
        }).show()
    }

    override fun showAddMaterialBindingDialog(materialList: List<Material>) {
        val view = initMaterialBindingDialogView(materialList)
        dialog = showCustomViewDialogWithoutButtons(context!!, view, getString(R.string.addMaterial)).show()
    }

    @SuppressLint("InflateParams")
    private fun initMaterialDialogView(): View {
        val view = layoutInflater.inflate(R.layout.dialog_material, null)
        view.unit.adapter = ArrayAdapter<String>(context!!, R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.materialUnits))
        return view
    }

    @SuppressLint("InflateParams")
    private fun initMaterialBindingDialogView(materialList: List<Material>): View {
        val view = layoutInflater.inflate(R.layout.dialog_add_object_binding, null)
        val adapter = ObjectMaterialsAdapter(context, { presenter.onAddMaterialClicked(it) })
        adapter.items = materialList
        view.bindingsList.adapter = adapter
        view.createButton.text = getString(R.string.createMaterial)
        view.createButton.setOnClickListener{ presenter.onCreateMaterialClicked() }
        return view
    }

    override fun hideAddMaterialBindingDialog() {
        dialog.cancel()
    }

    override fun setMaterialsList(items: List<Material>) {
        adapter.items = items
    }

    override fun getObjectId() = (activity!!.intent
            .getSerializableExtra(getString(R.string.keyObjectModel)) as ObjectModel).id

    override fun getProjectId() = (activity!!.intent
            .getSerializableExtra(getString(R.string.keyObjectModel)) as ObjectModel).projectId

    override fun notifyDataSetChanged() = adapter.notifyDataSetChanged()

}
package alesk.com.masterhelper.presentation.project.materials

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.buildCustomViewDialog
import alesk.com.masterhelper.application.utils.showAskingDialog
import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.databinding.ActivityMaterialsBinding
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_material.view.*
import javax.inject.Inject

class MaterialsActivity : BaseActivity(), MaterialsView, MaterialsRouter {

    @Inject
    lateinit var presenter: MaterialsPresenter
    lateinit var binding: ActivityMaterialsBinding
    lateinit var adapter: MaterialsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_materials)
        inject()
        initPresenter()
        supportActionBar?.title = getString(R.string.materials)
        adapter = MaterialsAdapter(this, { it, pos -> presenter.onDeleteMaterial(it, pos) },
                { isChecked, it -> presenter.onMaterialStatusChanged(isChecked, it) })
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

    override fun initPresenter() {
        presenter.view = this
        presenter.router = this
    }

    override fun showAddMaterialDialog(onOk: (String, Double?, String) -> Unit) {
        val view = initMaterialDialogView()
        buildCustomViewDialog(this, view, getString(R.string.addMaterial)) { _, _ ->
            onOk(view.name.text.toString(),
                    view.quantity.text.toString().toDoubleOrNull(),
                    view.unit.selectedItem.toString())
        }.show()
    }

    @SuppressLint("InflateParams")
    private fun initMaterialDialogView(): View {
        val view = layoutInflater.inflate(R.layout.dialog_material, null)
        view.unit.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.materialUnits))
        return view
    }

    override fun askForDeleting(material: Material, position: Int) {
        showAskingDialog(this, getString(R.string.sureForDeletingMaterial), getString(R.string.delete)){
            presenter.deleteMaterial(material, position)
        }
    }

    override fun setMaterialsList(materials: List<Material>) {
        adapter.items = materials
    }

    override fun notifyItemRemoved(pos: Int) = adapter.notifyItemRemoved(pos)

    override fun getProjectId(): Long {
        return intent.getLongExtra(getString(R.string.keyIdProject), 0)
    }

    override fun updateViewBindings() = binding.invalidateAll()

}

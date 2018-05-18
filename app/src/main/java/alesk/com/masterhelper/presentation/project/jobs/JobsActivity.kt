package alesk.com.masterhelper.presentation.project.jobs

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.UNIT_DROPDOWN_WIDTH
import alesk.com.masterhelper.application.utils.showCustomViewDialog
import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.databinding.ActivityJobsBinding
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_job.view.*
import javax.inject.Inject

class JobsActivity : AppCompatActivity(), JobsView, JobsRouter {

    @Inject
    lateinit var presenter: JobsPresenter
    lateinit var binding: ActivityJobsBinding
    lateinit var adapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_jobs)
        inject()
        initPresenter()
        supportActionBar?.title = getString(R.string.jobs)
        adapter = JobsAdapter(this, { it, pos -> presenter.onEditJob(it, pos) },
                                            { isChecked, it -> presenter.onJobStatusChanged(isChecked, it) })
    }

    override fun onStart() {
        super.onStart()
        binding.presenter = presenter
        binding.adapter = adapter
        presenter.onStart()
    }

    fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    fun initPresenter(){
        presenter.view = this
        presenter.router = this
    }

    override fun showAddJobDialog(onOk: (String, Double?, String) -> Unit) {
        val view = initJobDialogView()
        showCustomViewDialog(this, view, getString(R.string.addJob), { d, i ->
            onOk(view.name.text.toString(),
                  view.quantity.text.toString().toDoubleOrNull(),
                  view.unit.selectedItem.toString())
        }).show()
    }

    override fun showEditJobDialog(name: String, quantity: Double, unit: String,
                                   onOk: (String, Double?, String) -> Unit) {
        val view = initJobDialogView()
        view.name.setText(name)
        view.quantity.setText(quantity.toString())
        view.unit.setSelection((view.unit.adapter as ArrayAdapter<String>).getPosition(unit))
        showCustomViewDialog(this, view, getString(R.string.editJob), { d, i ->
            onOk(view.name.text.toString(),
                    view.quantity.text.toString().toDoubleOrNull(),
                    view.unit.selectedItem.toString())
        }).show()
    }

    @SuppressLint("InflateParams")
    private fun initJobDialogView(): View {
        val view = layoutInflater.inflate(R.layout.dialog_job, null)
        view.unit.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.units))
        view.unit.dropDownWidth = UNIT_DROPDOWN_WIDTH
        return view
    }

    override fun setJobsList(jobs: List<Job>) {
        adapter.items = jobs
    }

    override fun getProjectId(): Long {
        return intent.getLongExtra(getString(R.string.keyIdProject), 0)
    }

    override fun updateViewBindings() = binding.invalidateAll()

    override fun notifyItemChanged(pos: Int) = adapter.notifyItemChanged(pos)

}

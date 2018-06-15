package alesk.com.masterhelper.presentation.project.jobs

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.buildCustomViewDialog
import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.databinding.ActivityJobsBinding
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import android.annotation.SuppressLint
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.dialog_job.view.*
import javax.inject.Inject

class JobsActivity : BaseActivity(), JobsView, JobsRouter {

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

    override fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    override fun initPresenter(){
        presenter.view = this
        presenter.router = this
    }

    override fun showAddJobDialog(onOk: (String, Double?, String) -> Unit) {
        val view = initJobDialogView()
        buildCustomViewDialog(this, view, getString(R.string.addJob)) { d, i ->
            onOk(view.name.text.toString(),
                    view.quantity.text.toString().toDoubleOrNull(),
                    view.unit.selectedItem.toString())
        }.show()
    }

    override fun showEditJobDialog(name: String, quantity: Double, unit: String,
                                   onOk: (String, Double?, String) -> Unit) {
        val view = initJobDialogView()
        view.name.setText(name)
        view.quantity.setText(quantity.toString())
        view.unit.setSelection((view.unit.adapter as ArrayAdapter<String>).getPosition(unit))
        buildCustomViewDialog(this, view, getString(R.string.editJob)) { d, i ->
            onOk(view.name.text.toString(),
                    view.quantity.text.toString().toDoubleOrNull(),
                    view.unit.selectedItem.toString())
        }.show()
    }

    @SuppressLint("InflateParams")
    private fun initJobDialogView(): View {
        val view = layoutInflater.inflate(R.layout.dialog_job, null)
        view.unit.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.jobUnits))
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

package alesk.com.masterhelper.presentation.project.objects.projectObject.jobs

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.buildCustomViewDialog
import alesk.com.masterhelper.application.utils.buildCustomViewDialogWithoutButtons
import alesk.com.masterhelper.data.entities.Job
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

class ObjectJobsFragment: BaseFragment<ObjectJobsPresenter, ObjectJobsView, ObjectRouter>(), ObjectJobsView {

    lateinit var adapter: ObjectJobsAdapter
    lateinit var dialog: android.app.AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_object_bindings, container, false)
        view.addButton.text = getString(R.string.addJob)
        view.addButton.setOnClickListener { presenter.onAddJobBinding() }
        adapter = ObjectJobsAdapter(context) { presenter.onJobClicked(it) }
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

    override fun showCreateJobDialog(onCreate: (String, Double?, String) -> Unit) {
        val view = initJobDialogView()
        buildCustomViewDialog(context!!, view, getString(R.string.addJob)) { _, _ ->
            onCreate(view.name.text.toString(),
                    view.quantity.text.toString().toDoubleOrNull(),
                    view.unit.selectedItem.toString())
        }.show()
    }

    override fun showAddJobBindingDialog(jobList: List<Job>) {
        val view = initJobBindingDialogView(jobList)
        dialog = buildCustomViewDialogWithoutButtons(context!!, view, getString(R.string.addJob)).show()
    }

    @SuppressLint("InflateParams")
    private fun initJobDialogView(): View {
        val view = layoutInflater.inflate(R.layout.dialog_job, null)
        view.unit.adapter = ArrayAdapter<String>(context!!, R.layout.support_simple_spinner_dropdown_item,
                resources.getStringArray(R.array.jobUnits))
        return view
    }

    @SuppressLint("InflateParams")
    private fun initJobBindingDialogView(jobList: List<Job>): View {
        val view = layoutInflater.inflate(R.layout.dialog_add_object_binding, null)
        val adapter = ObjectJobsAdapter(context) { presenter.onAddJobClicked(it) }
        adapter.items = jobList
        view.bindingsList.adapter = adapter
        view.createButton.text = getString(R.string.createJob)
        view.createButton.setOnClickListener { presenter.onCreateJobClicked() }
        return view
    }

    override fun hideAddJobBindingDialog() {
        dialog.cancel()
    }

    override fun setJobsList(items: List<Job>) {
        adapter.items = items
    }

    override fun getObjectId() = (activity!!.intent
            .getSerializableExtra(getString(R.string.keyObjectModel)) as ObjectModel).id

    override fun getProjectId() = (activity!!.intent
            .getSerializableExtra(getString(R.string.keyObjectModel)) as ObjectModel).projectId

    override fun notifyDataSetChanged() = adapter.notifyDataSetChanged()

}
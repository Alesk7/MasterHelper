package alesk.com.masterhelper.presentation.project.jobs

import alesk.com.masterhelper.data.entities.Job

interface JobsView {
    fun setJobsList(jobs: List<Job>)
    fun getProjectId(): Long
    fun showAddJobDialog(onOk: (String, Double?, String) -> Unit)
    fun showEditJobDialog(name: String, quantity: Double, unit: String,
                          onOk: (String, Double?, String) -> Unit)
    fun updateViewBindings()
    fun notifyItemChanged(pos: Int)
}
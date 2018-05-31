package alesk.com.masterhelper.presentation.project.objects.projectObject.jobs

import alesk.com.masterhelper.data.entities.Job

interface ObjectJobsView {
    fun setJobsList(items: List<Job>)
    fun getObjectId(): Long
    fun getProjectId(): Long
    fun showAddJobBindingDialog(jobList: List<Job>)
    fun showCreateJobDialog(onCreate: (String, Double?, String) -> Unit)
    fun notifyDataSetChanged()
    fun hideAddJobBindingDialog()
}
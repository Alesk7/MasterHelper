package alesk.com.masterhelper.presentation.project

interface ProjectView {
    fun getProjectId(): Long
    fun askForDeleting()
    fun setProjectName(name: String)
    fun getProjectNameString(): String
    fun showEditDialog(title: String, body: String, onSave: (String) -> Unit)
    fun showProgressBar()
    fun hideProgressBar()
    fun showDocumentGeneratedDialog(generatedFilePath: String)
    fun showPrintDialog()
    fun setArchivedColor()
    fun setPrimaryColor()
    fun setArchiveMenuItem()
    fun setUnarchiveMenuItem()
    fun openDocFolder()
}
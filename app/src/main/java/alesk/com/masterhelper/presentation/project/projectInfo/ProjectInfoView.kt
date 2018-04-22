package alesk.com.masterhelper.presentation.project.projectInfo

interface ProjectInfoView {
    fun getProjectPK(): String
    fun getProjectNameString(): String
    fun getProjectDescriptionString(): String
    fun getProjectAddressString(): String
    fun setProjectName(name: String)
    fun showEditDialog(title: String, body: String, onSave: (String) -> Unit)
    fun updateViewBindings()
    fun askForDeleting()
    fun close()
}
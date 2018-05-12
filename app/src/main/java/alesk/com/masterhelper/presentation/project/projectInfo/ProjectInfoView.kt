package alesk.com.masterhelper.presentation.project.projectInfo

interface ProjectInfoView {
    fun getProjectId(): Long
    fun getProjectDescriptionString(): String
    fun getProjectAddressString(): String
    fun showEditDialog(title: String, body: String, onSave: (String) -> Unit)
    fun updateViewBindings()
    fun tryMakeCall(number: String)
}
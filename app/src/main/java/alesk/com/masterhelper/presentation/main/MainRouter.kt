package alesk.com.masterhelper.presentation.main

interface MainRouter {
    fun showProjectsList()
    fun showCreateNewProject()
    fun showProjectInfo(id: Long)
    fun showMasterInfo()
}
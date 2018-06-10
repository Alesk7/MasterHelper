package alesk.com.masterhelper.presentation.main

interface MainRouter {
    fun showProjectsList()
    fun showCompletedProjectsList()
    fun showCreateNewProject()
    fun showProjectInfo(id: Long)
    fun showMasterInfo()
    fun showHelp()
}
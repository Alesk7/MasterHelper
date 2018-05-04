package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.presentation.models.ProjectModel

interface ProjectRouter {
    fun close()
    fun showClientInfo(projectModel: ProjectModel)
    fun showContractDetails()
}
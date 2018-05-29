package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.presentation.models.ObjectModel
import alesk.com.masterhelper.presentation.models.ProjectModel

interface ProjectRouter {
    fun close()
    fun showClientInfo(projectModel: ProjectModel)
    fun showContractDetails(projectModel: ProjectModel)
    fun showJobs(projectId: Long)
    fun showMaterials(projectId: Long)
    fun showObject(objectModel: ObjectModel)
    fun showPrices(projectId: Long)
}
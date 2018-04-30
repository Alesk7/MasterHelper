package alesk.com.masterhelper.presentation.project.clientInfo

import alesk.com.masterhelper.presentation.models.ProjectModel

interface ClientInfoView {
    fun getProjectModel(): ProjectModel
    fun setIndividualButtonActive()
    fun setOrganizationButtonActive()
    fun setIndividualCardVisible()
    fun setOrganizationCardVisible()
}
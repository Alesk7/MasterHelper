package alesk.com.masterhelper.presentation.project.contract

import alesk.com.masterhelper.presentation.models.ProjectModel

interface ContractView {
    fun getProjectModel(): ProjectModel
    fun updateViewBindings()
}
package alesk.com.masterhelper.presentation.project.contract.contractDetails

import alesk.com.masterhelper.presentation.models.ProjectModel

interface ContractDetailsView {
    fun showDatePicker(dateSet: (y: Int, m: Int, d: Int) -> Unit,
                       startYear: Int, startMonth: Int, startDay: Int)
    fun getProjectModel(): ProjectModel
    fun updateViewBindings()
}
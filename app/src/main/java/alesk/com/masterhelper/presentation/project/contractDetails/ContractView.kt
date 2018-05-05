package alesk.com.masterhelper.presentation.project.contractDetails

import alesk.com.masterhelper.presentation.models.ProjectModel

interface ContractView {
    fun getProjectModel(): ProjectModel
    fun showDatePicker(dateSet: (y: Int, m: Int, d: Int) -> Unit,
                       startYear: Int, startMonth: Int, startDay: Int)
    fun updateViewBindings()
}
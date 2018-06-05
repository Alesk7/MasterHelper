package alesk.com.masterhelper.presentation.project.contract.terms

import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.ProjectModel
import alesk.com.masterhelper.presentation.project.contract.ContractRouter
import javax.inject.Inject

class ContractTermsPresenter @Inject constructor(): BasePresenter<ContractTermsView, ContractRouter>() {

    lateinit var projectModel: ProjectModel

    override fun onStart() {
        projectModel = view!!.getProjectModel()
    }

}
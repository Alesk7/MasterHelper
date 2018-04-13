package alesk.com.masterhelper.presentation.main.masterInfo

import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.main.MainRouter
import javax.inject.Inject

class MasterInfoPresenter @Inject constructor(): BasePresenter<MasterInfoView, MainRouter>() {

    fun saveMasterInfo(){

    }

    fun setIndividual(){
        view?.setIndividualButtonActive()
        view?.setIndividualCardVisible()
    }

    fun setOrganization(){
        view?.setOrganizationButtonActive()
        view?.setOrganizationCardVisible()
    }

}
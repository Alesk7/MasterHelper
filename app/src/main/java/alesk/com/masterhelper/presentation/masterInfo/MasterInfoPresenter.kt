package alesk.com.masterhelper.presentation.masterInfo

import alesk.com.masterhelper.presentation.common.BasePresenter
import javax.inject.Inject

class MasterInfoPresenter @Inject constructor(): BasePresenter<MasterInfoView, MasterInfoRouter>() {

    fun onSaveMasterInfo(){
        router?.hideMasterInfo()
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
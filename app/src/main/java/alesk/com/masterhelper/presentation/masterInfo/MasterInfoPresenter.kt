package alesk.com.masterhelper.presentation.masterInfo

import alesk.com.masterhelper.domain.interactor.MasterInfoInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.MasterInfoModel
import alesk.com.masterhelper.presentation.models.mappers.MasterInfoModelMapper
import javax.inject.Inject

class MasterInfoPresenter @Inject constructor(
        private val interactor: MasterInfoInteractor,
        private val mapper: MasterInfoModelMapper
) : BasePresenter<MasterInfoView, MasterInfoRouter>() {

    lateinit var masterInfoModel: MasterInfoModel

    override fun onStart() {
        masterInfoModel = mapper.transform(interactor.getMasterInfo())
        if(masterInfoModel.isOrganization)
            setOrganization()
    }

    fun onSaveMasterInfo(){
        interactor.saveMasterInfo(mapper.transform(masterInfoModel))
        router?.hideMasterInfo()
    }

    fun setIndividual(){
        view?.setIndividualButtonActive()
        view?.setIndividualCardVisible()
        masterInfoModel.isOrganization = false
    }

    fun setOrganization(){
        view?.setOrganizationButtonActive()
        view?.setOrganizationCardVisible()
        masterInfoModel.isOrganization = true
    }

}
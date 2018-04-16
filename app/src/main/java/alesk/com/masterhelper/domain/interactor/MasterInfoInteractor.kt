package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.domain.repository.MasterInfoRepository
import javax.inject.Inject

class MasterInfoInteractor @Inject constructor(val masterInfoRepository: MasterInfoRepository) {

    fun getMasterInfo(): MasterInfo {
        return masterInfoRepository.getMasterInfo() ?: MasterInfo()
    }

    fun saveMasterInfo(masterInfo: MasterInfo) {
        masterInfoRepository.saveMasterInfo(masterInfo)
    }

}
package alesk.com.masterhelper.domain.repository

import alesk.com.masterhelper.data.entities.MasterInfo

interface MasterInfoRepository {
    fun getMasterInfo(): MasterInfo?
    fun saveMasterInfo(masterInfo: MasterInfo)
    fun updateMasterInfo(masterInfo: MasterInfo)
}
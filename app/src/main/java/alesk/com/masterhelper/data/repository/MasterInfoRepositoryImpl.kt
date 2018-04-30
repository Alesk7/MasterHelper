package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.dao.MasterInfoDAO
import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.domain.repository.MasterInfoRepository
import javax.inject.Inject

class MasterInfoRepositoryImpl @Inject constructor(
        val masterInfoDAO: MasterInfoDAO
): MasterInfoRepository {

    override fun saveMasterInfo(masterInfo: MasterInfo) {
        masterInfoDAO.saveMasterInfo(masterInfo)
    }

    override fun getMasterInfo(): MasterInfo? {
        return masterInfoDAO.getMasterInfo()
    }

}
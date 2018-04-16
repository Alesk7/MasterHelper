package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.domain.repository.MasterInfoRepository
import io.realm.Realm
import javax.inject.Inject

class MasterInfoRepositoryImpl @Inject constructor(val realm: Realm): MasterInfoRepository {

    override fun saveMasterInfo(masterInfo: MasterInfo) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(masterInfo)
        realm.commitTransaction()
    }

    override fun getMasterInfo(): MasterInfo? {
        return realm.where(MasterInfo::class.java).findFirst()
    }

}
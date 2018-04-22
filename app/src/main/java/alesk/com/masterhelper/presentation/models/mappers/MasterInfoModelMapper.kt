package alesk.com.masterhelper.presentation.models.mappers

import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.presentation.models.MasterInfoModel
import javax.inject.Inject

class MasterInfoModelMapper @Inject constructor() {

    fun transform(masterInfo: MasterInfo): MasterInfoModel {
        return MasterInfoModel(
                masterInfo.isOrganization,
                masterInfo.name,
                masterInfo.address,
                if (masterInfo.zipcode != 0L) masterInfo.zipcode.toString() else "",
                masterInfo.phoneNumber,
                masterInfo.passport,
                masterInfo.passportIssued,
                masterInfo.insuranceCertificate,
                if (masterInfo.UNP != 0L) masterInfo.UNP.toString() else "",
                masterInfo.bankAccount,
                masterInfo.bankName,
                if (masterInfo.bankCode != 0) masterInfo.bankCode.toString() else "",
                masterInfo.bankAddress
        )
    }

    fun transform(masterInfoModel: MasterInfoModel): MasterInfo{
        return MasterInfo(
                masterInfoModel.isOrganization,
                masterInfoModel.name,
                masterInfoModel.address,
                if(masterInfoModel.zipcode.isNotEmpty()) masterInfoModel.zipcode.toLong() else 0,
                masterInfoModel.phoneNumber,
                masterInfoModel.passport,
                masterInfoModel.passportIssued,
                masterInfoModel.insuranceCertificate,
                if(masterInfoModel.UNP.isNotEmpty()) masterInfoModel.UNP.toLong() else 0,
                masterInfoModel.bankAccount,
                masterInfoModel.bankName,
                if(masterInfoModel.bankCode.isNotEmpty()) masterInfoModel.bankCode.toInt() else 0,
                masterInfoModel.bankAddress
        )
    }

}
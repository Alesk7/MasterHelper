package alesk.com.masterhelper.data.entities

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class MasterInfo(
        var isOrganization: Boolean = false,
        var name: String = "",
        var address: String = "",
        var zipcode: Long = 0,
        var phoneNumber: String = "",
        var passport: String = "",
        var passportIssued: String = "",
        var insuranceCertificate: String = "",
        var UNP: Long = 0,
        var bankAccount: String = "",
        var bankName: String = "",
        var bankCode: Int = 0,
        var bankAddress: String = ""
): RealmModel {
    @PrimaryKey
    var id: Int = 1
}
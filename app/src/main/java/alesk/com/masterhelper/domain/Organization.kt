package alesk.com.masterhelper.domain

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Organization: RealmModel {
    var UNP: Long = 0
    var bankAccount: String = ""
    var bankName: String = ""
    var bankCode: Int = 0
    var bankAddress: Address? = Address()
}
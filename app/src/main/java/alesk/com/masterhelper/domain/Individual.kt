package alesk.com.masterhelper.domain

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Individual: RealmModel {
    var passport: String = ""
    var passportIssued: String = ""
    var insuranceCertificate: String = ""
}
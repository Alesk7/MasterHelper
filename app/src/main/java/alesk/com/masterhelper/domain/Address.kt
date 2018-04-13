package alesk.com.masterhelper.domain

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Address: RealmModel {
    var city: String = ""
    var street: String = ""
    var building: String = ""
    var apartment: String = ""
}
package alesk.com.masterhelper.domain

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Person: RealmModel {
    var name: String = ""
    var address: Address? = Address()
    var zipcode: Long = 0
    var phoneNumber: String = ""
}
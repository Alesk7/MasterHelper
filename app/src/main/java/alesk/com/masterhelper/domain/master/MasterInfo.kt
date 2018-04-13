package alesk.com.masterhelper.domain.master

import alesk.com.masterhelper.domain.Individual
import alesk.com.masterhelper.domain.Organization
import alesk.com.masterhelper.domain.Person
import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class MasterInfo: RealmModel {
    var person: Person? = Person()
    var individual: Individual? = null
    var organization: Organization? = null
}
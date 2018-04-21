package alesk.com.masterhelper.data.entities

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*

@RealmClass
open class Project(
        var name: String = "",
        var address: String = "",
        var jobsDescription: String = "",
        var client: Client? = Client()
): RealmModel {
    @PrimaryKey
    var PK: String = UUID.randomUUID().toString()
}
package alesk.com.masterhelper.data.entities

import io.realm.RealmModel
import io.realm.annotations.RealmClass

@RealmClass
open class Project(
        var name: String = "",
        var address: String = "",
        var jobDescription: String = "",
        var client: Client? = Client()
): RealmModel
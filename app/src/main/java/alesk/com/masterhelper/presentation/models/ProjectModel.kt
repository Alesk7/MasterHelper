package alesk.com.masterhelper.presentation.models

import java.util.*

class ProjectModel (
        var PK: String = UUID.randomUUID().toString(),
        var name: String = "",
        var address: String = "",
        var jobsDescription: String = "",
        var client: ClientModel = ClientModel()
)
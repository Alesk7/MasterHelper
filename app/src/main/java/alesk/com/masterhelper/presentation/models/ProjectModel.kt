package alesk.com.masterhelper.presentation.models

import java.io.Serializable

class ProjectModel (
        var id: Int = 0,
        var name: String = "",
        var address: String = "",
        var jobsDescription: String = "",
        var client: ClientModel = ClientModel()
): Serializable
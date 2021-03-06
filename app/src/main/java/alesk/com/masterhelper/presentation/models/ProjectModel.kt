package alesk.com.masterhelper.presentation.models

import java.io.Serializable

class ProjectModel (
        var id: Long = 0,
        var name: String = "",
        var address: String = "",
        var jobsDescription: String = "",
        var client: ClientModel = ClientModel(),
        var contract: ContractModel = ContractModel(),
        var isComplete: Boolean = false
): Serializable
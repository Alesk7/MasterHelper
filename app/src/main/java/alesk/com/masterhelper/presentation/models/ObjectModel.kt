package alesk.com.masterhelper.presentation.models

import java.io.Serializable

class ObjectModel(
        var id: Long = 0,
        var projectId: Long = 0,
        var name: String = "",
        var parentObjectId: Long? = null,
        var parentObjectName: String? = null
): Serializable
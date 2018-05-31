package alesk.com.masterhelper.presentation.project.objects.projectObject

import alesk.com.masterhelper.presentation.models.ObjectModel

interface ObjectView {
    fun setObjectName(name: String)
    fun getObjectModel(): ObjectModel
}
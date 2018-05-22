package alesk.com.masterhelper.presentation.models.mappers

import alesk.com.masterhelper.data.entities.ProjectObject
import alesk.com.masterhelper.presentation.models.ObjectModel
import javax.inject.Inject

class ObjectModelMapper @Inject constructor() {

    fun transform(objectModel: ObjectModel): ProjectObject {
        val projectObject = ProjectObject(objectModel.name)
        projectObject.id = objectModel.id
        projectObject.projectId = objectModel.projectId
        projectObject.parentObjectId = objectModel.parentObjectId
        return projectObject
    }

    fun transformToObjectList(objectModelsList: List<ObjectModel>): List<ProjectObject> {
        return objectModelsList.map {it ->
            val obj = ProjectObject(it.name)
            obj.id = it.id
            obj.projectId = it.projectId
            obj.parentObjectId = it.parentObjectId
            return@map obj
        }
    }

}
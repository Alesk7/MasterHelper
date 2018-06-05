package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.ProjectObject
import alesk.com.masterhelper.domain.repository.ProjectObjectRepository
import javax.inject.Inject

class ProjectObjectsInteractor
@Inject constructor (val projectObjectsRepository: ProjectObjectRepository) {

    fun addProjectObject(obj: ProjectObject) {
        projectObjectsRepository.addProjectObject(obj)
    }

    fun editProjectObject(obj: ProjectObject) {
        projectObjectsRepository.editProjectObject(obj)
    }

    fun getProjectObjectsByProjectId(projectId: Long): List<ProjectObject> {
        return projectObjectsRepository.getProjectObjectsByProjectId(projectId).asReversed()
    }

    fun getProjectObjectsByParentId(parentId: Long): List<ProjectObject> {
        return projectObjectsRepository.getProjectObjectsByParentId(parentId).asReversed()
    }

    fun getProjectObject(id: Long): ProjectObject {
        return projectObjectsRepository.getProjectObject(id) ?: ProjectObject()
    }

    fun deleteProjectObject(id: Long){
        projectObjectsRepository.deleteProjectObject(id)
    }

}
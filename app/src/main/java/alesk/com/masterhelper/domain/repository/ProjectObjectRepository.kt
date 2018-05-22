package alesk.com.masterhelper.domain.repository

import alesk.com.masterhelper.data.entities.ProjectObject

interface ProjectObjectRepository {
    fun getProjectObject(id: Long): ProjectObject?
    fun getProjectObjectsByProjectId(projectId: Long): List<ProjectObject>
    fun editProjectObject(obj: ProjectObject)
    fun addProjectObject(obj: ProjectObject)
}
package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.dao.ProjectObjectDAO
import alesk.com.masterhelper.data.entities.ProjectObject
import alesk.com.masterhelper.domain.repository.ProjectObjectRepository
import javax.inject.Inject

class ProjectObjectRepositoryImpl @Inject constructor(
        private val projectObjectDAO: ProjectObjectDAO
): ProjectObjectRepository {

    override fun getProjectObject(id: Long): ProjectObject? {
        return projectObjectDAO.getProjectObject(id)
    }

    override fun getProjectObjectsByProjectId(projectId: Long): List<ProjectObject> {
        return projectObjectDAO.getProjectObjectsByProjectId(projectId)
    }

    override fun getProjectObjectsByParentId(parentId: Long): List<ProjectObject> {
        return projectObjectDAO.getProjectObjectsByParentId(parentId)
    }

    override fun editProjectObject(obj: ProjectObject) {
        projectObjectDAO.editProjectObject(obj)
    }

    override fun addProjectObject(obj: ProjectObject) {
        projectObjectDAO.addProjectObject(obj)
    }

    override fun deleteProjectObject(id: Long) {
        projectObjectDAO.deleteProjectObject(id)
    }

}
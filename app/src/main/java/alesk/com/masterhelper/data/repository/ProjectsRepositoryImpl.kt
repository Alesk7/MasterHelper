package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.dao.ProjectDAO
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
        val projectDAO: ProjectDAO
): ProjectsRepository {

    override fun getAllProjects(): List<Project> {
        return projectDAO.getAllProjects()
    }

    override fun createProject(project: Project) {
        projectDAO.createProject(project)
    }

    override fun getProject(id: Long): Project? {
        return projectDAO.getProject(id)
    }

    override fun updateProject(project: Project) {
        projectDAO.updateProject(project)
    }

    override fun deleteProject(id: Long) {
        projectDAO.deleteProject(id)
    }

}
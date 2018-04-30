package alesk.com.masterhelper.domain.repository

import alesk.com.masterhelper.data.entities.Project

interface ProjectsRepository {
    fun getAllProjects(): List<Project>
    fun getProject(id: Int): Project?
    fun createProject(project: Project)
    fun updateProject(project: Project)
    fun deleteProject(id: Int)
}
package alesk.com.masterhelper.domain.repository

import alesk.com.masterhelper.data.entities.Project

interface ProjectsRepository {
    fun getAllProjects(): List<Project>
}
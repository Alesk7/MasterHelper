package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsInteractor @Inject constructor(private val projectsRepository: ProjectsRepository) {

    fun getNotCompletedProjects(): List<Project> {
        return projectsRepository.getNotCompletedProjects().asReversed()
    }

    fun getCompletedProjects(): List<Project> {
        return projectsRepository.getCompletedProjects().asReversed()
    }

    fun getProject(id: Long): Project {
        return projectsRepository.getProject(id) ?: Project()
    }

    fun createNewProject(project: Project) {
        projectsRepository.createProject(project)
    }

    fun updateProject(project: Project){
        projectsRepository.updateProject(project)
    }

    fun deleteProject(id: Long){
        projectsRepository.deleteProject(id)
    }

}
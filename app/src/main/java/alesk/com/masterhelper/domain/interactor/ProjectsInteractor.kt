package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import javax.inject.Inject

class ProjectsInteractor @Inject constructor(val projectsRepository: ProjectsRepository) {

    fun getProjects(): List<Project>{
        return projectsRepository.getAllProjects().asReversed()
    }

    fun createNewProject(project: Project){
        projectsRepository.createProject(project)
    }

}
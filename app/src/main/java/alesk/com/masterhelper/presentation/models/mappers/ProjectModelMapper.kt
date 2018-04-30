package alesk.com.masterhelper.presentation.models.mappers

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.presentation.models.ProjectModel
import javax.inject.Inject

class ProjectModelMapper @Inject constructor(val clientModelMapper: ClientModelMapper) {

    fun transform(projectModel: ProjectModel): Project {
        val project = Project(
                projectModel.name,
                projectModel.address,
                projectModel.jobsDescription
        )
        project.id = projectModel.id
        project.client = clientModelMapper.transform(projectModel.client)
        return project
    }

    fun transform(project: Project): ProjectModel {
        return ProjectModel(
                project.id,
                project.name,
                project.address,
                project.jobsDescription,
                clientModelMapper.transform(project.client)
        )
    }

}
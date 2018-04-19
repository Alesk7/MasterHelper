package alesk.com.masterhelper.presentation.main.newProject.model.mappers

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.presentation.main.newProject.model.ClientModel
import alesk.com.masterhelper.presentation.main.newProject.model.ProjectModel
import javax.inject.Inject

class ProjectModelMapper @Inject constructor(val clientModelMapper: ClientModelMapper) {

    fun transform(projectModel: ProjectModel, clientModel: ClientModel): Project {
        return Project(
                projectModel.name,
                projectModel.address,
                projectModel.jobsDescription,
                clientModelMapper.transform(clientModel)
        )
    }

}
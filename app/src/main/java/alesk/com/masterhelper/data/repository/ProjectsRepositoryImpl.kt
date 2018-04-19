package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import io.realm.Realm
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(val realm: Realm): ProjectsRepository {

    override fun getAllProjects(): List<Project> {
        return realm.where(Project::class.java).findAll()
    }

    override fun createProject(project: Project) {
        realm.beginTransaction()
        realm.copyToRealm(project)
        realm.commitTransaction()
    }

}
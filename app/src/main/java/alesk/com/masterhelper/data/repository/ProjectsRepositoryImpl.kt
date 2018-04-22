package alesk.com.masterhelper.data.repository

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import io.realm.Realm
import io.realm.RealmObject
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

    override fun getProject(PK: String): Project? {
        return realm.where(Project::class.java).equalTo("PK", PK).findFirst()
    }

    override fun updateProject(project: Project) {
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(project)
        realm.commitTransaction()
    }

    override fun deleteProject(PK: String) {
        realm.executeTransaction{
            val project = it.where(Project::class.java)
                    .equalTo("PK", PK)
                    .findFirst()
            RealmObject.deleteFromRealm(project!!)
        }
    }

}
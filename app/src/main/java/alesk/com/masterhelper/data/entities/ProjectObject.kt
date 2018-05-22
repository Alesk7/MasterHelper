package alesk.com.masterhelper.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(entity = Project::class,
                                   parentColumns = arrayOf("id"),
                                   childColumns = arrayOf("projectId"),
                                   onDelete = ForeignKey.CASCADE)),

                       (ForeignKey(entity = ProjectObject::class,
                                   parentColumns = arrayOf("id"),
                                   childColumns = arrayOf("parentObjectId"),
                                   onDelete = ForeignKey.CASCADE))])
data class ProjectObject(
        var name: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var projectId: Long = 0
    var parentObjectId: Long? = null
}
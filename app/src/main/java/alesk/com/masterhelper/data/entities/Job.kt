package alesk.com.masterhelper.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(entity = Project::class,
                                   parentColumns = arrayOf("id"),
                                   childColumns = arrayOf("projectId"),
                                   onDelete = CASCADE))])
data class Job(
        var name: String = "",
        var quantity: Double = 0.0,
        var unitPrice: Double = 0.0,
        var unit: String = "",
        var priceSum: Double = 0.0,
        var isComplete: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var projectId: Long = 0
    var objectId: Long? = null
}
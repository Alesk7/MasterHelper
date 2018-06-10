package alesk.com.masterhelper.data.entities

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Project(
        var name: String = "",
        var address: String = "",
        var jobsDescription: String = "",
        @Embedded(prefix = "client_")
        var client: Client = Client(),
        @Embedded(prefix = "contract_")
        var contract: Contract = Contract(),
        var isComplete: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
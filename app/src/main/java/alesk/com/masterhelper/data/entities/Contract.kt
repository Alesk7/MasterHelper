package alesk.com.masterhelper.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
class Contract(
        var contractDate: Long = Date().time,
        var workStartDate: Long = Date().time,
        var workEndDate: Long = Date().time + 10 * 86400,
        var workLength: Int = 10,
        var prepayment: Double = 0.0,
        var isMasterMaterialsSupplier: Boolean = true
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var number: Long = id
}
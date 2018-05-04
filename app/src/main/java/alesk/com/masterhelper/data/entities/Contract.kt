package alesk.com.masterhelper.data.entities

import alesk.com.masterhelper.data.entities.enums.MaterialsSupplier
import alesk.com.masterhelper.data.entities.enums.converters.MaterialsSupplierConverter
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import java.util.*

@Entity
class Contract(
        var number: Long = 1,
        var contractDate: Long = Date().time,
        var workStartDate: Long = Date().time,
        var workEndDate: Long = Date().time,
        var workLength: Int = 10,
        var prepayment: Double = 0.0,
        @TypeConverters(MaterialsSupplierConverter::class)
        var materialsSupplier: MaterialsSupplier = MaterialsSupplier.MASTER
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
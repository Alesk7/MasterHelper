package alesk.com.masterhelper.data.entities.enums.converters

import alesk.com.masterhelper.data.entities.enums.MaterialsSupplier
import android.arch.persistence.room.TypeConverter

class MaterialsSupplierConverter {

    @TypeConverter
    fun convert(supplier: MaterialsSupplier) = supplier.name

    @TypeConverter
    fun convert(s: String) = MaterialsSupplier.valueOf(s)

}
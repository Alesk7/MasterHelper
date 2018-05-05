package alesk.com.masterhelper.presentation.models

import android.annotation.SuppressLint
import java.io.Serializable
import java.util.*

@SuppressLint("SimpleDateFormat")
class ContractModel(
        var number: String = "",
        var workLength: String = "",
        var prepayment: String = "",
        var isMasterMaterialsSupplier: Boolean = true
): Serializable {
    var id: Long = 0
    var contractDate: Date = Date()
    var workStartDate: Date = Date()
    var workEndDate: Date = Date()
}
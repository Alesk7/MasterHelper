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
        var isMasterMaterialsSupplier: Boolean = true,
        var isSubcontractorsAllowed: Boolean = false,
        var guaranteeTerm: Int = 0,
        var workAcceptanceTerm: Int = 0,
        var paymentTerm: Int = 0,
        var todoWhenPausedTerm: Int = 0,
        var removeToolsTerm: Int = 0,
        var claimForWorkAcceptanceTerm: Int = 0,
        var noticeOfDefectsTerm: Int = 0,
        var defectsEliminationTerm: Int = 0,
        var forceMajeureTerm: Int = 0,
        var requisitesChangedTerm: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var number: Long = id
}
package alesk.com.masterhelper.data.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Client(
        var isOrganization: Boolean = false,
        var name: String = "",
        var address: String = "",
        var zipcode: Long = 0,
        var phoneNumber: String = "",
        var passport: String = "",
        var passportIssued: String = "",
        var insuranceCertificate: String = "",
        var UNP: Long = 0,
        var bankAccount: String = "",
        var bankName: String = "",
        var bankCode: Int = 0,
        var bankAddress: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
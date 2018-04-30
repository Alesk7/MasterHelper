package alesk.com.masterhelper.presentation.models

import java.io.Serializable

class ClientModel (
        var id: Int = 0,
        var isOrganization: Boolean = false,
        var name: String = "",
        var address: String = "",
        var zipcode: String = "",
        var phoneNumber: String = "",
        var passport: String = "",
        var passportIssued: String = "",
        var insuranceCertificate: String = "",
        var UNP: String = "",
        var bankAccount: String = "",
        var bankName: String = "",
        var bankCode: String = "",
        var bankAddress: String = ""
): Serializable
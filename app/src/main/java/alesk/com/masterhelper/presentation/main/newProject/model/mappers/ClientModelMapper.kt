package alesk.com.masterhelper.presentation.main.newProject.model.mappers

import alesk.com.masterhelper.data.entities.Client
import alesk.com.masterhelper.presentation.main.newProject.model.ClientModel
import javax.inject.Inject

class ClientModelMapper @Inject constructor() {

    fun transform(clientModel: ClientModel): Client {
        return Client(
                clientModel.isOrganization,
                clientModel.name,
                clientModel.address,
                if(clientModel.zipcode.isNotEmpty()) clientModel.zipcode.toLong() else 0,
                clientModel.phoneNumber,
                clientModel.passport,
                clientModel.passportIssued,
                clientModel.insuranceCertificate,
                if(clientModel.UNP.isNotEmpty()) clientModel.UNP.toLong() else 0,
                clientModel.bankAccount,
                clientModel.bankName,
                if(clientModel.bankCode.isNotEmpty()) clientModel.bankCode.toInt() else 0,
                clientModel.bankAddress
        )
    }

}
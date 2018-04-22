package alesk.com.masterhelper.presentation.models.mappers

import alesk.com.masterhelper.data.entities.Client
import alesk.com.masterhelper.presentation.models.ClientModel
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

    fun transform(client: Client): ClientModel {
        return ClientModel(
                client.isOrganization,
                client.name,
                client.address,
                if (client.zipcode != 0L) client.zipcode.toString() else "",
                client.phoneNumber,
                client.passport,
                client.passportIssued,
                client.insuranceCertificate,
                if (client.UNP != 0L) client.UNP.toString() else "",
                client.bankAccount,
                client.bankName,
                if (client.bankCode != 0) client.bankCode.toString() else "",
                client.bankAddress
        )
    }

}
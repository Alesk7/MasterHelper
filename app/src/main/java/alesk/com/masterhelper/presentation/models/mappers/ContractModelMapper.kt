package alesk.com.masterhelper.presentation.models.mappers

import alesk.com.masterhelper.data.entities.Contract
import alesk.com.masterhelper.presentation.models.ContractModel
import java.util.*
import javax.inject.Inject

class ContractModelMapper @Inject constructor() {

    fun transform(contractModel: ContractModel): Contract {
        val contract = Contract(
                contractModel.contractDate.time,
                contractModel.workStartDate.time,
                contractModel.workEndDate.time,
                if(contractModel.workLength.isNotEmpty()) contractModel.workLength.toInt() else 7,
                if(contractModel.prepayment.isNotEmpty()) contractModel.prepayment.toDouble() else 0.0,
                contractModel.isMasterMaterialsSupplier
        )
        contract.id = contractModel.id
        contract.number = if(contractModel.number.isNotEmpty()) contractModel.number.toLong() else 0
        return contract
    }

    fun transform(contract: Contract): ContractModel {
        val contractModel = ContractModel(
                contract.number.toString(),
                contract.workLength.toString(),
                contract.prepayment.toString(),
                contract.isMasterMaterialsSupplier
        )
        contractModel.contractDate = Date(contract.contractDate)
        contractModel.workStartDate = Date(contract.workStartDate)
        contractModel.workEndDate = Date(contract.workEndDate)
        contractModel.id = contract.id
        return contractModel
    }

}
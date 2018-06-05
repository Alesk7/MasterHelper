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
                contractModel.isMasterMaterialsSupplier,
                contractModel.isSubcontractorsAllowed,
                if(contractModel.guaranteeTerm.isNotEmpty()) contractModel.guaranteeTerm.toInt() else 0,
                if(contractModel.workAcceptanceTerm.isNotEmpty()) contractModel.workAcceptanceTerm.toInt() else 0,
                if(contractModel.paymentTerm.isNotEmpty()) contractModel.paymentTerm.toInt() else 0,
                if(contractModel.todoWhenPausedTerm.isNotEmpty()) contractModel.todoWhenPausedTerm.toInt() else 0,
                if(contractModel.removeToolsTerm.isNotEmpty()) contractModel.removeToolsTerm.toInt() else 0,
                if(contractModel.claimForWorkAcceptanceTerm.isNotEmpty()) contractModel.claimForWorkAcceptanceTerm.toInt() else 0,
                if(contractModel.noticeOfDefectsTerm.isNotEmpty()) contractModel.noticeOfDefectsTerm.toInt() else 0,
                if(contractModel.defectsEliminationTerm.isNotEmpty()) contractModel.defectsEliminationTerm.toInt() else 0,
                if(contractModel.forceMajeureTerm.isNotEmpty()) contractModel.forceMajeureTerm.toInt() else 0,
                if(contractModel.requisitesChangedTerm.isNotEmpty()) contractModel.requisitesChangedTerm.toInt() else 0
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
                contract.isMasterMaterialsSupplier,
                contract.isSubcontractorsAllowed,
                contract.guaranteeTerm.toString(),
                contract.workAcceptanceTerm.toString(),
                contract.paymentTerm.toString(),
                contract.todoWhenPausedTerm.toString(),
                contract.removeToolsTerm.toString(),
                contract.claimForWorkAcceptanceTerm.toString(),
                contract.noticeOfDefectsTerm.toString(),
                contract.defectsEliminationTerm.toString(),
                contract.forceMajeureTerm.toString(),
                contract.requisitesChangedTerm.toString()
        )
        contractModel.contractDate = Date(contract.contractDate)
        contractModel.workStartDate = Date(contract.workStartDate)
        contractModel.workEndDate = Date(contract.workEndDate)
        contractModel.id = contract.id
        return contractModel
    }

}
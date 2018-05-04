package alesk.com.masterhelper.presentation.models

import alesk.com.masterhelper.data.entities.enums.MaterialsSupplier

class ContractModel(
        var number: String,
        var contractDate: String,
        var workStartDate: String,
        var workEndDate: String,
        var workLength: String,
        var prepayment: String,
        var materialsSupplier: MaterialsSupplier = MaterialsSupplier.MASTER
) {
    var id: Long = 0
}
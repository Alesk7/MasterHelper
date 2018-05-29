package alesk.com.masterhelper.presentation.models

class MaterialModel(
        var id: Long = 0,
        var projectId: Long = 0,
        var name: String = "",
        var quantity: Double = 0.0,
        var unitPrice: Double = 0.0,
        var unit: String = "",
        var priceSum: Double = 0.0,
        var isPurchased: Boolean = false
)
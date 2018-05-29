package alesk.com.masterhelper.presentation.models.mappers

import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.presentation.models.MaterialModel
import javax.inject.Inject

class MaterialModelMapper @Inject constructor() {

    fun transform(material: Material): MaterialModel {
        return MaterialModel(
                material.id,
                material.projectId,
                material.name,
                material.quantity,
                material.unitPrice,
                material.unit,
                material.priceSum,
                material.isPurchased
        )
    }

    fun transform(materialModel: MaterialModel): Material {
        val material = Material(
                materialModel.name,
                materialModel.quantity,
                materialModel.unitPrice,
                materialModel.unit,
                materialModel.priceSum,
                materialModel.isPurchased
        )
        material.id = materialModel.id
        material.projectId = materialModel.projectId
        return material
    }

}
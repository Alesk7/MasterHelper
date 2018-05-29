package alesk.com.masterhelper.presentation.project.prices.materialPrices

import alesk.com.masterhelper.presentation.models.MaterialModel

interface MaterialPricesView {
    fun getProjectId(): Long
    fun setMaterialsList(items: List<MaterialModel>)
}
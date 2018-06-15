package alesk.com.masterhelper.presentation.project.prices.materialPrices

import alesk.com.masterhelper.domain.interactor.MaterialsInteractor
import alesk.com.masterhelper.presentation.common.BasePresenter
import alesk.com.masterhelper.presentation.models.MaterialModel
import alesk.com.masterhelper.presentation.models.mappers.MaterialModelMapper
import alesk.com.masterhelper.presentation.project.prices.PricesRouter
import javax.inject.Inject

class MaterialPricesPresenter @Inject constructor(
        private val materialsInteractor: MaterialsInteractor,
        private val materialModelMapper: MaterialModelMapper
): BasePresenter<MaterialPricesView, PricesRouter>() {

    lateinit var materialsList: List<MaterialModel>

    override fun onStart() {
        materialsList = materialsInteractor.getMaterialsByProjectId(view!!.getProjectId()).map {
            materialModelMapper.transform(it)
        }
        view?.setMaterialsList(materialsList)
    }

    fun onPriceChanged(jobModel: MaterialModel){
        materialsInteractor.editMaterial(materialModelMapper.transform(jobModel))
    }

}
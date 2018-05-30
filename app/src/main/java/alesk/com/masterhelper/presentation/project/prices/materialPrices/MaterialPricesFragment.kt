package alesk.com.masterhelper.presentation.project.prices.materialPrices

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.FragmentMaterialPricesBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.MaterialModel
import alesk.com.masterhelper.presentation.project.prices.PricesRouter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MaterialPricesFragment: BaseFragment<MaterialPricesPresenter, MaterialPricesView, PricesRouter>(),
        MaterialPricesView {

    lateinit var binding: FragmentMaterialPricesBinding
    lateinit var adapter: MaterialPricesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_material_prices, container, false)
        adapter = MaterialPricesAdapter(context, { presenter.onPriceChanged(it) })
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.adapter = adapter
        presenter.onStart()
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun setMaterialsList(items: List<MaterialModel>) {
        adapter.items = items
    }

    override fun getProjectId(): Long {
        return activity!!.intent!!.getLongExtra(getString(R.string.keyIdProject), 0)
    }

}
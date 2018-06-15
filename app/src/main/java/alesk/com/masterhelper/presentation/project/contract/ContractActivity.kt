package alesk.com.masterhelper.presentation.project.contract

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.ActivityContractBinding
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ProjectModel
import android.databinding.DataBindingUtil
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contract.*
import javax.inject.Inject

class ContractActivity : BaseActivity(), ContractView, ContractRouter {

    @Inject
    lateinit var presenter: ContractPresenter
    lateinit var binding: ActivityContractBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contract)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.contract)
        inject()
        initPresenter()
        viewPager.adapter = ContractPagerAdapter(supportFragmentManager)
        viewPager.pageMargin = resources.getDimension(R.dimen.viewPagerMargin).toInt()
        initTabLayout()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
    }

    override fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    override fun initPresenter(){
        presenter.router = this
        presenter.view = this
    }

    private fun initTabLayout(){
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.text = getString(R.string.contractDetails)
        tabLayout.getTabAt(1)?.text = getString(R.string.terms)
    }

    override fun getProjectModel(): ProjectModel {
        return intent.getSerializableExtra(getString(R.string.keyProjectModel)) as ProjectModel
    }

    override fun hideContractDetails() = finish()

    override fun updateViewBindings() = binding.invalidateAll()

}

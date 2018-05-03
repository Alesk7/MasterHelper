package alesk.com.masterhelper.presentation.project.clientInfo

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.ActivityClientInfoBinding
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ProjectModel
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_client_info.*
import javax.inject.Inject

class ClientInfoActivity : BaseActivity(), ClientInfoView, ClientInfoRouter {

    @Inject
    lateinit var presenter: ClientInfoPresenter
    lateinit var binding: ActivityClientInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_client_info)
        supportActionBar?.title = getString(R.string.clientDetails)
        inject()
        initPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.client = presenter.projectModel.client
    }

    private fun inject(){
        DaggerPresentationComponent.create().inject(this)
    }

    private fun initPresenter(){
        presenter.view = this
        presenter.router = this
    }

    override fun hideClientInfo() {
        finish()
    }

    override fun getProjectModel(): ProjectModel {
        return intent.getSerializableExtra(getString(R.string.keyProjectModel)) as ProjectModel
    }

    override fun setIndividualButtonActive() {
        individualButton.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        individualButton.setTextColor(Color.WHITE)
        organizationButton.setBackgroundColor(Color.TRANSPARENT)
        organizationButton.setTextColor(Color.BLACK)
    }

    override fun setOrganizationButtonActive() {
        organizationButton.setBackgroundColor(resources.getColor(R.color.colorAccent))
        organizationButton.setTextColor(Color.WHITE)
        individualButton.setBackgroundColor(Color.TRANSPARENT)
        individualButton.setTextColor(Color.BLACK)
    }

    override fun setIndividualCardVisible() {
        individualInfoCard.visibility = View.VISIBLE
        organizationInfoCard.visibility = View.GONE
    }

    override fun setOrganizationCardVisible() {
        organizationInfoCard.visibility = View.VISIBLE
        individualInfoCard.visibility = View.GONE
    }

}

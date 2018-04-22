package alesk.com.masterhelper.presentation.main.newProject

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.FragmentNewProjectBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.injection.modules.InteractorModule
import alesk.com.masterhelper.presentation.main.MainRouter
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.fragment_new_project.*
import javax.inject.Inject

class NewProjectFragment : BaseFragment(), NewProjectView {

    @Inject
    lateinit var presenter: NewProjectPresenter
    lateinit var binding: FragmentNewProjectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_project, container, false)
        setActionBarTitle(getString(R.string.newProject))
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.presenter = presenter
        binding.project = presenter.projectModel
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.new_project_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.ok_item -> presenter.onSaveNewProject()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun inject() {
        DaggerPresentationComponent
                .builder()
                .interactorModule(InteractorModule())
                .build()
                .inject(this)
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.router = activity as MainRouter
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

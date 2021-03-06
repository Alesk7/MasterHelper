package alesk.com.masterhelper.presentation.main.newProject

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.showShortCustomToast
import alesk.com.masterhelper.databinding.FragmentNewProjectBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.main.MainRouter
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.fragment_new_project.*
import kotlinx.android.synthetic.main.toast_custom.*

class NewProjectFragment : BaseFragment<NewProjectPresenter, NewProjectView, MainRouter>(), NewProjectView {

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

    override fun hide() {
        fragmentManager?.popBackStack()
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun setIndividualButtonActive() {
        individualButton.setBackgroundColor(resources.getColor(R.color.colorAccentSecondary))
        individualButton.setTextColor(Color.WHITE)
        organizationButton.setBackgroundColor(Color.TRANSPARENT)
        organizationButton.setTextColor(Color.BLACK)
    }

    override fun setOrganizationButtonActive() {
        organizationButton.setBackgroundColor(resources.getColor(R.color.colorAccentSecondary))
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

    override fun showEmptyNameMessage() {
        showShortCustomToast(context,
                layoutInflater.inflate(R.layout.toast_custom, root),
                getString(R.string.nameEmptyMessage))
    }
}

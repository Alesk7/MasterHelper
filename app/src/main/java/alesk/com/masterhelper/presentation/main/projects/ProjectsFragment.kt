package alesk.com.masterhelper.presentation.main.projects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.databinding.FragmentProjectsBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.main.MainRouter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*

class ProjectsFragment : BaseFragment<ProjectsPresenter, ProjectsView, MainRouter>(), ProjectsView {

    lateinit var binding: FragmentProjectsBinding
    lateinit var adapter: ProjectsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)
        setActionBarTitle(getString(R.string.projects))
        setHasOptionsMenu(true)
        adapter = ProjectsAdapter(context, { presenter.onProjectSelected(it) } )
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.masterDetails_item -> { presenter.onShowMasterInfo() }
            R.id.archived_item -> {}
        }
        return false
    }

    override fun setProjectsList(projectsList: List<Project>) {
        adapter.items = projectsList
    }

    override fun onStart() {
        super.onStart()
        binding.presenter = presenter
        binding.adapter = adapter
        presenter.onStart()
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

}

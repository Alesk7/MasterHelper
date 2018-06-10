package alesk.com.masterhelper.presentation.main.projects.completedProjects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.databinding.FragmentCompletedProjectsBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.main.MainActivity
import alesk.com.masterhelper.presentation.main.MainRouter
import alesk.com.masterhelper.presentation.main.projects.ProjectsAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CompletedProjectsFragment:
        BaseFragment<CompletedProjectsPresenter, CompletedProjectsView, MainRouter>(), CompletedProjectsView {

    lateinit var binding: FragmentCompletedProjectsBinding
    lateinit var adapter: ProjectsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_completed_projects, container, false)
        setActionBarTitle(getString(R.string.completedProjects))
        (activity as MainActivity).updateActionBarColor(resources.getColor(R.color.colorCompletedProjectsPrimary))
        (activity as MainActivity).updateStatusBarColor(resources.getColor(R.color.colorCompletedProjectsPrimaryDark))
        adapter = ProjectsAdapter(context, { presenter.onProjectSelected(it) } )
        return binding.root
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

    override fun setProjectsList(projectsList: List<Project>) {
        adapter.items = projectsList
    }

}
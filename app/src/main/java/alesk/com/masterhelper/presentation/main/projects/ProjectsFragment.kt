package alesk.com.masterhelper.presentation.main.projects

import alesk.com.masterhelper.R
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.databinding.FragmentProjectsBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.injection.modules.InteractorModule
import alesk.com.masterhelper.presentation.main.MainRouter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class ProjectsFragment : BaseFragment(), ProjectsView {

    @Inject
    lateinit var presenter: ProjectsPresenter
    lateinit var binding: FragmentProjectsBinding
    lateinit var adapter: ProjectsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_projects, container, false)
        setActionBarTitle(getString(R.string.projects))
        adapter = ProjectsAdapter(context, { presenter.onProjectSelected(it) } )
        return binding.root
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

}

package alesk.com.masterhelper.presentation.project.projectInfo

import alesk.com.masterhelper.R
import alesk.com.masterhelper.databinding.FragmentProjectInfoBinding
import alesk.com.masterhelper.presentation.common.BaseFragment
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.injection.modules.InteractorModule
import alesk.com.masterhelper.presentation.project.ProjectRouter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

class ProjectInfoFragment: BaseFragment(), ProjectInfoView {

    @Inject
    lateinit var presenter: ProjectInfoPresenter
    lateinit var binding: FragmentProjectInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_info, container, false)
        return binding.root
    }

    override fun setProjectName(name: String) {
        setActionBarTitle(name)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
        binding.project = presenter.project
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
        presenter.router = activity as ProjectRouter
    }

    override fun getProjectPK(): String {
        return activity!!.intent!!.getStringExtra(getString(R.string.keyIdProject))
    }

}
package alesk.com.masterhelper.presentation.main.newProject

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.common.BaseFragment
import android.os.Bundle
import android.view.*

class NewProjectFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setActionBarTitle(getString(R.string.newProject))
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_new_project, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.new_project_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun inject() {

    }

    override fun initPresenter() {

    }
}

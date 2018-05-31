package alesk.com.masterhelper.presentation.project.objects.projectObject

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.common.BaseActivity
import alesk.com.masterhelper.presentation.injection.DaggerPresentationComponent
import alesk.com.masterhelper.presentation.models.ObjectModel
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_object.*
import javax.inject.Inject

class ObjectActivity : BaseActivity(), ObjectView, ObjectRouter {

    @Inject
    lateinit var presenter: ObjectPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)
        setSupportActionBar(toolbar)
        inject()
        initPresenter()
        viewPager.adapter = ObjectPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)?.text = getString(R.string.projectObjects)
        tabs.getTabAt(1)?.text = getString(R.string.jobs)
        tabs.getTabAt(2)?.text = getString(R.string.materials)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun inject() {
        DaggerPresentationComponent.create().inject(this)
    }

    override fun initPresenter() {
        presenter.view = this
        presenter.router = this
    }

    override fun setObjectName(name: String) {
        supportActionBar?.title = name
    }

    override fun getObjectModel() = intent
            .getSerializableExtra(getString(R.string.keyObjectModel)) as ObjectModel

}

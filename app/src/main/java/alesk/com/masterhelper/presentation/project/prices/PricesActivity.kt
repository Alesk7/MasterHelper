package alesk.com.masterhelper.presentation.project.prices

import alesk.com.masterhelper.R
import alesk.com.masterhelper.presentation.common.BaseActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_prices.*

class PricesActivity : BaseActivity(), PricesRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prices)
        supportActionBar?.title = getString(R.string.prices)
        viewPager.adapter = PricesPagerAdapter(supportFragmentManager)
        viewPager.pageMargin = 16
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)?.text = getString(R.string.jobs)
        tabLayout.getTabAt(1)?.text = getString(R.string.materials)
    }

}

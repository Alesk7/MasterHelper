package alesk.com.masterhelper.presentation.project.prices

import alesk.com.masterhelper.presentation.project.prices.jobPrices.JobPricesFragment
import alesk.com.masterhelper.presentation.project.prices.materialPrices.MaterialPricesFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

const val NUM_PAGES = 2

class PricesPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> MaterialPricesFragment()
            else -> JobPricesFragment()
        }
    }

    override fun getCount() = NUM_PAGES

}
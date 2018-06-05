package alesk.com.masterhelper.presentation.project.contract

import alesk.com.masterhelper.presentation.project.contract.contractDetails.ContractDetailsFragment
import alesk.com.masterhelper.presentation.project.contract.terms.ContractTermsFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

const val NUM_PAGES = 2

class ContractPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return NUM_PAGES
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> ContractTermsFragment()
            else -> ContractDetailsFragment()
        }
    }

}
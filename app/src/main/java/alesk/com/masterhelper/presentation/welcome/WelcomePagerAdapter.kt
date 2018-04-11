package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.presentation.main.personInfo.PersonInfoFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class WelcomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val NUM_PAGES = 2

    override fun getCount(): Int {
        return NUM_PAGES
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> PersonInfoFragment()
            else -> WelcomeFragment()
        }
    }

}
package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.presentation.project.objects.ObjectsFragment
import alesk.com.masterhelper.presentation.project.projectInfo.ProjectInfoFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

const val NUM_PAGES = 2

class ProjectPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int {
        return NUM_PAGES
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> ObjectsFragment()
            else -> ProjectInfoFragment()
        }
    }

}
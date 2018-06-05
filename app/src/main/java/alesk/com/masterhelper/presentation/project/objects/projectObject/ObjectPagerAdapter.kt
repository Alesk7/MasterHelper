package alesk.com.masterhelper.presentation.project.objects.projectObject

import alesk.com.masterhelper.presentation.project.objects.projectObject.jobs.ObjectJobsFragment
import alesk.com.masterhelper.presentation.project.objects.projectObject.materials.ObjectMaterialsFragment
import alesk.com.masterhelper.presentation.project.objects.projectObject.objects.BindedObjectsFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

const val NUM_PAGES = 3

class ObjectPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    override fun getCount() = NUM_PAGES

    override fun getItem(position: Int): Fragment {
        return when(position){
            1 -> ObjectJobsFragment()
            2 -> ObjectMaterialsFragment()
            else -> BindedObjectsFragment()
        }
    }

}
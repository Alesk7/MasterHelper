package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.R
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.welcome_slide.view.*

class WelcomePagerAdapter(val context: Context) : PagerAdapter() {

    val welcomeView: WelcomeView = context as Welcome
    val viewPagerLayouts = arrayOf(R.layout.welcome_slide,
                                   R.layout.master_details)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun getCount(): Int {
        return viewPagerLayouts.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(viewPagerLayouts[position], container, false)
        setPageContent(position, view)
        container.addView(view)
        return view
    }

    fun setPageContent(position: Int, view: View){
        when(position){
            0 -> view.buttonNext.setOnClickListener({ welcomeView.setWelcomePage(position + 1) })
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}
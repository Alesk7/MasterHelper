package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.R
import alesk.com.masterhelper.application.utils.getOptimizedBitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.welcome_slide.view.*

class WelcomePagerAdapter(val context: Context) : PagerAdapter() {

    val welcomeView: WelcomeView = context as Welcome

    val welcomeTitles = context.resources.getStringArray(R.array.welcomeTitles)
    val welcomeTexts = context.resources.getStringArray(R.array.welcomeTexts)
    val welcomeButtonLabels = context.resources.getStringArray(R.array.welcomeButtonLabels)
    val welcomeImages = context.resources.obtainTypedArray(R.array.welcomeImages)
    val welcomePrimaryColors = context.resources.obtainTypedArray(R.array.welcomePrimaryColors)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun getCount(): Int {
        return welcomeTitles.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.welcome_slide, container, false)
        changePageContent(view, position)
        container.addView(view)
        return view
    }

    fun changePageContent(view: View, pageNumber: Int){
        view.welcomeTitle.text = welcomeTitles[pageNumber]
        view.welcomeText.text = welcomeTexts[pageNumber]
        view.welcomeImage.setImageBitmap(getOptimizedImage(pageNumber))
        view.buttonNext.text = welcomeButtonLabels[pageNumber]
        view.rootLayout.setBackgroundColor(context.resources.getColor(
                welcomePrimaryColors.getResourceId(pageNumber, -1)))
    }

    fun getOptimizedImage(index: Int): Bitmap {
        val bitmap = context.resources.getDrawable(
                welcomeImages.getResourceId(index, -1)) as BitmapDrawable
        return getOptimizedBitmap(bitmap, welcomeView.getWindowWidth())
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
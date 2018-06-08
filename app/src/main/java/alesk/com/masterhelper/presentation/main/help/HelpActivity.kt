package alesk.com.masterhelper.presentation.main.help

import alesk.com.masterhelper.R
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        supportActionBar?.title = getString(R.string.help)
        image1.setImageDrawable(getDrawableFromAssets("image1.png"))
        image2.setImageDrawable(getDrawableFromAssets("image2.png"))
    }

    private fun getDrawableFromAssets(fileName: String): Drawable {
        val inputStream = assets.open(fileName)
        return Drawable.createFromStream(inputStream, null)
    }

}

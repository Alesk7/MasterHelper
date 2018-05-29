package alesk.com.masterhelper.presentation.project.objects.projectObject

import alesk.com.masterhelper.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_object.*

class ObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object)
        setSupportActionBar(toolbar)
    }

}

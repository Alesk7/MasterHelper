package alesk.com.masterhelper.presentation.project

import alesk.com.masterhelper.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_project.*

class ProjectActivity : AppCompatActivity(), ProjectRouter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project)
        setSupportActionBar(toolbar)
    }

}

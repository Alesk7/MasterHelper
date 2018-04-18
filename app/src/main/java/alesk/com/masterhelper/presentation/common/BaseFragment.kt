package alesk.com.masterhelper.presentation.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initPresenter()
    }

    abstract fun inject()
    abstract fun initPresenter()

    fun setActionBarTitle(title: String){
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

}
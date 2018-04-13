package alesk.com.masterhelper.presentation.common

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initPresenter()
    }

    abstract fun inject()
    abstract fun initPresenter()

}
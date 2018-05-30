package alesk.com.masterhelper.presentation.common

import alesk.com.masterhelper.R
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    inline fun FragmentManager.inTransaction(isAddToBackStack: Boolean, func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fragment_enter_anim, R.anim.fragment_exit_anim,
                R.anim.fragment_pop_enter_anim, R.anim.fragment_pop_exit_anim)
        if(isAddToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    open fun inject(){}

    open fun initPresenter(){}

}
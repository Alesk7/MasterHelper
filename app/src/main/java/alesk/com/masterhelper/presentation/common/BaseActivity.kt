package alesk.com.masterhelper.presentation.common

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    inline fun FragmentManager.inTransaction(isAddToBackStack: Boolean, func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        if(isAddToBackStack) fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}
package alesk.com.masterhelper.presentation.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseFragment<Presenter: BasePresenter<V, R>, V, R>: Fragment() {

    @Inject
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initPresenter()
    }

    abstract fun inject()

    fun initPresenter(){
        presenter.view = this as V
        presenter.router = activity as R
    }

    fun setActionBarTitle(title: String){
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

}
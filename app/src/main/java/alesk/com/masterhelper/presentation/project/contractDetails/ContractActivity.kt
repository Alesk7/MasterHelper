package alesk.com.masterhelper.presentation.project.contractDetails

import alesk.com.masterhelper.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ContractActivity : AppCompatActivity(), ContractView {

    lateinit var presenter: ContractPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract)
        supportActionBar?.title = getString(R.string.contractDetails)
    }

    fun inject(){

    }

    fun initPresenter(){

    }

}

package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class WelcomeFragment : Fragment() {

    lateinit var welcomeRouter: WelcomeRouter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)
        welcomeRouter = activity as WelcomeActivity
        view.buttonNext.setOnClickListener { welcomeRouter.nextWelcomePage() }
        return view
    }

}

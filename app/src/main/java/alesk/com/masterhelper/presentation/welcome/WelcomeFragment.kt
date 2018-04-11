package alesk.com.masterhelper.presentation.welcome

import alesk.com.masterhelper.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.welcome_fragment.view.*

class WelcomeFragment : Fragment() {

    lateinit var welcomeView: WelcomeView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.welcome_fragment, container, false)
        welcomeView = activity as WelcomeActivity
        view.buttonNext.setOnClickListener({ welcomeView.nextWelcomePage() })
        return view
    }

}

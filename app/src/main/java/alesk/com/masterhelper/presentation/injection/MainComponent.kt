package alesk.com.masterhelper.presentation.injection

import alesk.com.masterhelper.presentation.masterInfo.MasterInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component()
interface MainComponent {
    fun inject(fragment: MasterInfoFragment)
}
package alesk.com.masterhelper.presentation.injection

import alesk.com.masterhelper.presentation.injection.modules.InteractorModule
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InteractorModule::class])
interface PresentationComponent {
    fun inject(fragment: MasterInfoFragment)
}
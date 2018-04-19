package alesk.com.masterhelper.presentation.injection

import alesk.com.masterhelper.presentation.injection.modules.InteractorModule
import alesk.com.masterhelper.presentation.main.newProject.NewProjectFragment
import alesk.com.masterhelper.presentation.main.projects.ProjectsFragment
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InteractorModule::class])
interface PresentationComponent {
    fun inject(fragment: MasterInfoFragment)
    fun inject(fragment: ProjectsFragment)
    fun inject(fragment: NewProjectFragment)
}
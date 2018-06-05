package alesk.com.masterhelper.presentation.injection

import alesk.com.masterhelper.presentation.injection.modules.InteractorModule
import alesk.com.masterhelper.presentation.main.newProject.NewProjectFragment
import alesk.com.masterhelper.presentation.main.projects.ProjectsFragment
import alesk.com.masterhelper.presentation.masterInfo.MasterInfoFragment
import alesk.com.masterhelper.presentation.project.ProjectActivity
import alesk.com.masterhelper.presentation.project.clientInfo.ClientInfoActivity
import alesk.com.masterhelper.presentation.project.contract.ContractActivity
import alesk.com.masterhelper.presentation.project.contract.contractDetails.ContractDetailsFragment
import alesk.com.masterhelper.presentation.project.contract.terms.ContractTermsFragment
import alesk.com.masterhelper.presentation.project.jobs.JobsActivity
import alesk.com.masterhelper.presentation.project.materials.MaterialsActivity
import alesk.com.masterhelper.presentation.project.objects.ObjectsFragment
import alesk.com.masterhelper.presentation.project.objects.projectObject.ObjectActivity
import alesk.com.masterhelper.presentation.project.objects.projectObject.jobs.ObjectJobsFragment
import alesk.com.masterhelper.presentation.project.objects.projectObject.materials.ObjectMaterialsFragment
import alesk.com.masterhelper.presentation.project.objects.projectObject.objects.BindedObjectsFragment
import alesk.com.masterhelper.presentation.project.prices.jobPrices.JobPricesFragment
import alesk.com.masterhelper.presentation.project.prices.materialPrices.MaterialPricesFragment
import alesk.com.masterhelper.presentation.project.projectInfo.ProjectInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [InteractorModule::class])
interface PresentationComponent {
    fun inject(fragment: MasterInfoFragment)
    fun inject(fragment: ProjectsFragment)
    fun inject(fragment: NewProjectFragment)
    fun inject(fragment: ProjectInfoFragment)
    fun inject(activity: ClientInfoActivity)
    fun inject(activity: ContractActivity)
    fun inject(activity: ProjectActivity)
    fun inject(activity: JobsActivity)
    fun inject(fragment: ObjectsFragment)
    fun inject(activity: MaterialsActivity)
    fun inject(fragment: JobPricesFragment)
    fun inject(fragment: MaterialPricesFragment)
    fun inject(fragment: ObjectJobsFragment)
    fun inject(activity: ObjectActivity)
    fun inject(fragment: ObjectMaterialsFragment)
    fun inject(fragment: BindedObjectsFragment)
    fun inject(fragment: ContractDetailsFragment)
    fun inject(fragment: ContractTermsFragment)
}
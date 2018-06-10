package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.docGenerators.ActDocGenerator
import alesk.com.masterhelper.domain.docGenerators.ContractDocGenerator
import alesk.com.masterhelper.domain.docGenerators.PriceEstimateDocGenerator
import alesk.com.masterhelper.domain.repository.JobRepository
import alesk.com.masterhelper.domain.repository.MasterInfoRepository
import alesk.com.masterhelper.domain.repository.MaterialRepository
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import io.reactivex.Single
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class DocumentsInteractor @Inject constructor(
        val jobRepository: JobRepository,
        val materialRepository: MaterialRepository,
        val projectsRepository: ProjectsRepository,
        val masterInfoRepository: MasterInfoRepository
) {

    val ESTIMATE_PATH_PATTERN = "%sСмета_%s №%d.docx"
    val CONTRACT_PATH_PATTERN = "%sДоговор_%s №%d.docx"
    val ACT_PATH_PATTERN = "%sАкт_%s №%d.docx"

    init{
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl")
        System.setProperty("javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl")
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl")
    }

    fun printPrices(projectId: Long): Single<String> {
        val project = projectsRepository.getProject(projectId)
        val jobs = jobRepository.getJobsByProjectId(projectId)
        val materials = materialRepository.getMaterialsByProjectId(projectId)
        val masterInfo = masterInfoRepository.getMasterInfo()
        val priceEstimateDocGenerator = PriceEstimateDocGenerator(masterInfo!!, project!!, jobs, materials)
        return if(project.contract.isMasterMaterialsSupplier)
            Single.defer { Single.just(
                    saveDoc(priceEstimateDocGenerator.generate(), ESTIMATE_PATH_PATTERN, project)) }
        else
            Single.defer { Single.just(
                    saveDoc(priceEstimateDocGenerator.generateWithoutMaterials(), ESTIMATE_PATH_PATTERN, project)) }
    }

    fun printContract(projectId: Long): Single<String> {
        val project = projectsRepository.getProject(projectId)
        val masterInfo = masterInfoRepository.getMasterInfo()
        val contractDocGenerator = ContractDocGenerator(project!!, masterInfo!!)
        return Single.defer {
            Single.just(saveDoc(contractDocGenerator.generate(), CONTRACT_PATH_PATTERN, project)) }
    }

    fun printAct(projectId: Long): Single<String> {
        val project = projectsRepository.getProject(projectId)
        val jobs = jobRepository.getJobsByProjectId(projectId)
        val materials = materialRepository.getMaterialsByProjectId(projectId)
        val masterInfo = masterInfoRepository.getMasterInfo()
        val actDocGenerator = ActDocGenerator(project!!, masterInfo!!, jobs, materials)
        return Single.defer {
            Single.just(saveDoc(actDocGenerator.generate(), ACT_PATH_PATTERN, project)) }
    }

    private fun saveDoc(doc: XWPFDocument, pathPattern: String, project: Project): String {
        val path = String.format(pathPattern,
                applicationComponent.getDocumentsPath(), project.name, project.contract.number)
        val out = FileOutputStream(path)
        createDocumentsDirIfNotExists()
        File(path).createNewFile()
        doc.write(out)
        out.close(); doc.close()
        return path
    }

    private fun createDocumentsDirIfNotExists() {
        val docDir = File(applicationComponent.getDocumentsPath())
        if(!docDir.exists()) docDir.mkdir()
    }

}
package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.application.utils.DOCUMENTS_PATH
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
        private val jobRepository: JobRepository,
        private val materialRepository: MaterialRepository,
        private val projectsRepository: ProjectsRepository,
        private val masterInfoRepository: MasterInfoRepository
) {

    private val estimatePathPattern = "%sСмета_%s №%d.docx"
    private val contractPathPattern = "%sДоговор_%s №%d.docx"
    private val actPathPattern = "%sАкт_%s №%d.docx"

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
                    saveDoc(priceEstimateDocGenerator.generate(), estimatePathPattern, project)) }
        else
            Single.defer { Single.just(
                    saveDoc(priceEstimateDocGenerator.generateWithoutMaterials(), estimatePathPattern, project)) }
    }

    fun printContract(projectId: Long): Single<String> {
        val project = projectsRepository.getProject(projectId)
        val masterInfo = masterInfoRepository.getMasterInfo()
        val contractDocGenerator = ContractDocGenerator(project!!, masterInfo!!)
        return Single.defer {
            Single.just(saveDoc(contractDocGenerator.generate(), contractPathPattern, project)) }
    }

    fun printAct(projectId: Long): Single<String> {
        val project = projectsRepository.getProject(projectId)
        val jobs = jobRepository.getJobsByProjectId(projectId)
        val materials = materialRepository.getMaterialsByProjectId(projectId)
        val masterInfo = masterInfoRepository.getMasterInfo()
        val actDocGenerator = ActDocGenerator(project!!, masterInfo!!, jobs, materials)
        return Single.defer {
            Single.just(saveDoc(actDocGenerator.generate(), actPathPattern, project)) }
    }

    private fun saveDoc(doc: XWPFDocument, pathPattern: String, project: Project): String {
        val path = String.format(pathPattern, DOCUMENTS_PATH, project.name, project.contract.number)
        val out = FileOutputStream(path)
        createDocumentsDirIfNotExists()
        File(path).createNewFile()
        doc.write(out)
        out.close(); doc.close()
        return path
    }

    private fun createDocumentsDirIfNotExists() {
        val docDir = File(DOCUMENTS_PATH)
        if(!docDir.exists()) docDir.mkdir()
    }

}
package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.docGenerators.PriceEstimateGenerator
import alesk.com.masterhelper.domain.repository.JobRepository
import alesk.com.masterhelper.domain.repository.MasterInfoRepository
import alesk.com.masterhelper.domain.repository.MaterialRepository
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import android.os.Environment
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
    val DOCUMENTS_PATH = "${Environment.getExternalStorageDirectory()}${File.separator}${Environment.DIRECTORY_DOCUMENTS}${File.separator}"

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
        val priceEstimateGenerator = PriceEstimateGenerator(masterInfo!!, project!!, jobs, materials)

        return if(project.contract.isMasterMaterialsSupplier)
            Single.defer { Single.just(
                    saveDoc(priceEstimateGenerator.generate(), ESTIMATE_PATH_PATTERN, project)) }
        else
            Single.defer { Single.just(
                    saveDoc(priceEstimateGenerator.generateWithoutMaterials(), ESTIMATE_PATH_PATTERN, project)) }
    }

    private fun saveDoc(doc: XWPFDocument, pathPattern: String, project: Project): String {
        val path = String.format(pathPattern, DOCUMENTS_PATH, project.name, project.contract.number)
        createDocumentsDirIfNotExists()
        File(path).createNewFile()
        val out = FileOutputStream(path)
        doc.write(out)
        out.close(); doc.close()
        return path
    }

    private fun createDocumentsDirIfNotExists() {
        val docDir = File(DOCUMENTS_PATH)
        if(!docDir.exists()) docDir.mkdir()
    }

}
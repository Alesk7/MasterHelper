package alesk.com.masterhelper.domain.interactor

import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.docGenerators.PriceEstimateGenerator
import alesk.com.masterhelper.domain.repository.JobRepository
import alesk.com.masterhelper.domain.repository.MasterInfoRepository
import alesk.com.masterhelper.domain.repository.MaterialRepository
import alesk.com.masterhelper.domain.repository.ProjectsRepository
import android.os.Environment
import android.util.Log
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

    init{
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl")
        System.setProperty("javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl")
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl")
    }

    fun printPrices(projectId: Long): String {
        val project = projectsRepository.getProject(projectId)
        val jobs = jobRepository.getJobsByProjectId(projectId)
        val materials = materialRepository.getMaterialsByProjectId(projectId)
        val masterInfo = masterInfoRepository.getMasterInfo()
        val priceEstimateGenerator = PriceEstimateGenerator(masterInfo!!, project!!, jobs, materials)

        return if(project.contract.isMasterMaterialsSupplier)
            saveDoc(priceEstimateGenerator.generate(), ESTIMATE_PATH_PATTERN, project)
        else
            saveDoc(priceEstimateGenerator.generateWithoutMaterials(), ESTIMATE_PATH_PATTERN, project)
    }

    private fun saveDoc(doc: XWPFDocument, pathPattern: String, project: Project): String {
        val path = String.format(pathPattern,
                "${Environment.getExternalStorageDirectory()}${File.separator}${Environment.DIRECTORY_DOCUMENTS}${File.separator}",
                project.name, project.contract.number)
        Log.i("PATH", path)
        File(path).createNewFile()
        val out = FileOutputStream(path)
        doc.write(out)
        out.close(); doc.close()
        return path
    }

}
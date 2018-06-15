package alesk.com.masterhelper.domain.docGenerators

import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.application.utils.defaultDateFormat
import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.calculateEstimateSum
import alesk.com.masterhelper.domain.calculateJobsSum
import alesk.com.masterhelper.domain.calculateMaterialsSum
import alesk.com.masterhelper.domain.docGenerators.consts.*
import alesk.com.masterhelper.domain.docGenerators.helpers.EMPTY_ROW_POSITION
import alesk.com.masterhelper.domain.docGenerators.helpers.createItemRow
import alesk.com.masterhelper.domain.docGenerators.helpers.replaceText
import alesk.com.masterhelper.domain.docGenerators.helpers.setCellText
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFTable
import java.util.*

class PriceEstimateDocGenerator(
        private val masterInfo: MasterInfo,
        private val project: Project,
        private val jobs: List<Job>,
        private val materials: List<Material>
) {

    private val doc: XWPFDocument

    init { doc = getEstimateDocument() }

    fun generate(): XWPFDocument {
        fillFields()
        fillJobsTable(doc.tables[1])
        fillMaterialsTable(doc.tables[2])
        return doc
    }

    fun generateWithoutMaterials(): XWPFDocument {
        fillFields()
        fillJobsTable(doc.tables[1])
        return doc
    }

    private fun fillFields() {
        replaceText(doc, contractNumber, project.contract.number.toString())
        replaceText(doc, contractDate, defaultDateFormat.format(Date(project.contract.contractDate)))
        replaceText(doc, city, project.address)
        replaceText(doc, clientName, project.client.name)
        replaceText(doc, masterName, masterInfo.name)
        replaceText(doc, estimateSum, String.format("%.2f", calculateEstimateSum(jobs, materials)))
        replaceText(doc, estimateDate, defaultDateFormat.format(Date()))
    }

    private fun fillJobsTable(table: XWPFTable) {
        for(i in jobs.indices) {
            val newRow = createItemRow(table)
            setCellText(newRow, 0, (i + 1).toString())
            setCellText(newRow, 1, jobs[i].name)
            setCellText(newRow, 2, jobs[i].unit)
            setCellText(newRow, 3, jobs[i].quantity.toString())
            setCellText(newRow, 4, String.format("%.2f", jobs[i].unitPrice))
            setCellText(newRow, 5, String.format("%.2f", jobs[i].priceSum))
            table.addRow(newRow, EMPTY_ROW_POSITION + 1 + i)
        }
        setCellText(table.rows[table.rows.size - 1], 1, String.format("%.2f", calculateJobsSum(jobs)))
        table.removeRow(EMPTY_ROW_POSITION)
    }

    private fun fillMaterialsTable(table: XWPFTable) {
        for(i in materials.indices) {
            val newRow = createItemRow(table)
            setCellText(newRow, 0, (i + 1).toString())
            setCellText(newRow, 1, materials[i].name)
            setCellText(newRow, 2, materials[i].unit)
            setCellText(newRow, 3, materials[i].quantity.toString())
            setCellText(newRow, 4, String.format("%.2f", materials[i].unitPrice))
            setCellText(newRow, 5, String.format("%.2f", materials[i].priceSum))
            table.addRow(newRow, EMPTY_ROW_POSITION + 1 + i)
        }
        setCellText(table.rows[table.rows.size - 1], 1, String.format("%.2f", calculateMaterialsSum(materials)))
        table.removeRow(EMPTY_ROW_POSITION)
    }

    private fun getEstimateDocument(): XWPFDocument {
        val estimateFile = applicationComponent.getAssets().open(estimateFileName)
        return XWPFDocument(estimateFile)
    }

}
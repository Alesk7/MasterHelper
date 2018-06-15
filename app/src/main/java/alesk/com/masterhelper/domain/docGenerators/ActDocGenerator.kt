package alesk.com.masterhelper.domain.docGenerators

import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.application.utils.defaultDateFormat
import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.calculateEstimateSum
import alesk.com.masterhelper.domain.calculateJobsSum
import alesk.com.masterhelper.domain.docGenerators.consts.*
import alesk.com.masterhelper.domain.docGenerators.helpers.EMPTY_ROW_POSITION
import alesk.com.masterhelper.domain.docGenerators.helpers.createItemRow
import alesk.com.masterhelper.domain.docGenerators.helpers.replaceText
import alesk.com.masterhelper.domain.docGenerators.helpers.setCellText
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFTable
import java.util.*

class ActDocGenerator(
        private val project: Project,
        private val masterInfo: MasterInfo,
        private val jobs: List<Job>,
        private val materials: List<Material>
) {

    private val doc: XWPFDocument

    init { doc = getActDocument() }

    fun generate(): XWPFDocument {
        fillFields()
        fillJobsTable(doc.tables[1])
        return doc
    }

    private fun fillFields() {
        fillContractDetails()
        fillActors()
        replaceText(doc, city, project.address)
        replaceText(doc, actDate, defaultDateFormat.format(Date()))
        fillEstimateSum()
    }

    private fun fillContractDetails(){
        repeat(3) { replaceText(doc, contractNumber, project.contract.number.toString()) }
        repeat(3) { replaceText(doc, contractDate,
                defaultDateFormat.format(Date(project.contract.contractDate))) }
    }

    private fun fillActors() {
        repeat(2) { replaceText(doc, clientName, project.client.name) }
        repeat(2) { replaceText(doc, masterName, masterInfo.name) }
        replaceText(doc, masterAddress, masterInfo.address)
        replaceText(doc, clientAddress, project.client.address)
    }

    private fun fillEstimateSum(){
        var sum = if(project.contract.isMasterMaterialsSupplier)
            calculateEstimateSum(jobs, materials) else calculateJobsSum(jobs)
        sum -= project.contract.prepayment
        repeat(2) { replaceText(doc, estimateSum, String.format("%.2f", sum)) }
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

    private fun getActDocument(): XWPFDocument {
        val contractFile = applicationComponent.getAssets().open(actFileName)
        return XWPFDocument(contractFile)
    }

}
package alesk.com.masterhelper.domain.docGenerators

import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.data.entities.Job
import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.data.entities.Material
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.calculateEstimateSum
import alesk.com.masterhelper.domain.calculateJobsSum
import alesk.com.masterhelper.domain.calculateMaterialsSum
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFRun
import org.apache.poi.xwpf.usermodel.XWPFTable
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow
import java.util.*

const val EMPTY_ROW_POSITION = 1

class PriceEstimateGenerator(
        val masterInfo: MasterInfo,
        val project: Project,
        val jobs: List<Job>,
        val materials: List<Material>
) {

    private lateinit var doc: XWPFDocument

    fun generate(): XWPFDocument {
        doc = getEstimateDocument()
        fillFields()
        fillJobsTable(doc.tables[1])
        fillMaterialsTable(doc.tables[2])
        return doc
    }

    fun generateWithoutMaterials(): XWPFDocument {
        doc = getEstimateDocument()
        fillFields()
        fillJobsTable(doc.tables[1])
        return doc
    }

    private fun fillFields() {
        replaceText("ContractNumber", project.contract.number.toString())
        replaceText("ContractDate",
                applicationComponent.getSimpleDateFormat().format(Date(project.contract.contractDate)))
        replaceText("City", project.address)
        replaceText("ClientName", project.client.name)
        replaceText("MasterName", masterInfo.name)
        replaceText("EstimateSum", calculateEstimateSum(jobs, materials).toString())
        replaceText("EstimateDate", applicationComponent.getSimpleDateFormat().format(Date()))
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

    private fun replaceText(s: String, replacement: String) {
        val matches = searchMatchesJustInParagraphs(s)
        matches.forEach {
            it.setText(it.getText(0).replace(s, replacement), 0)
        }

        if(matches.isEmpty()) {
            searchMatchesInTables(s).forEach {
                it.setText(it.getText(0).replace(s, replacement), 0)
            }
        }
    }

    private fun searchMatchesJustInParagraphs(s: String): List<XWPFRun> {
        val matches = ArrayList<XWPFRun>()
        doc.paragraphs.forEach { paragraph ->
            val runs = paragraph.runs
            runs.forEach {
                val text = it.getText(0)
                if (text != null && text.contains(s))
                    matches.add(it)
            }
        }
        return matches
    }

    private fun searchMatchesInTables(s: String): List<XWPFRun> {
        val matches = ArrayList<XWPFRun>()
        doc.tables.forEach { table ->
            table.rows.forEach { row ->
                row.tableCells.forEach { cell ->
                    cell.paragraphs.forEach {
                        it.runs.forEach {
                            val text = it.getText(0)
                            if (text != null && text.contains(s))
                                matches.add(it)
                        }
                    }
                }
            }
        }
        return matches
    }

    private fun setCellText(row: XWPFTableRow, pos: Int, string: String) {
        row.getCell(pos).removeParagraph(0)
        val run = row.getCell(pos).addParagraph().createRun()
        run.isBold = false
        run.setText(string)
    }

    private fun createItemRow(table: XWPFTable): XWPFTableRow {
        val oldRow = table.getRow(EMPTY_ROW_POSITION)
        val ctrow = CTRow.Factory.parse(oldRow.ctRow.newInputStream())
        return XWPFTableRow(ctrow, table)
    }

    private fun getEstimateDocument(): XWPFDocument {
        val estimateFile = applicationComponent.getAssets().open("prices.docx")
        return XWPFDocument(estimateFile)
    }

}
package alesk.com.masterhelper.domain.docGenerators.helpers

import org.apache.poi.xwpf.usermodel.XWPFTable
import org.apache.poi.xwpf.usermodel.XWPFTableRow
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow

const val EMPTY_ROW_POSITION = 1

fun createItemRow(table: XWPFTable): XWPFTableRow {
    val oldRow = table.getRow(EMPTY_ROW_POSITION)
    val ctrow = CTRow.Factory.parse(oldRow.ctRow.newInputStream())
    return XWPFTableRow(ctrow, table)
}

fun setCellText(row: XWPFTableRow, pos: Int, string: String) {
    row.getCell(pos).removeParagraph(0)
    val run = row.getCell(pos).addParagraph().createRun()
    run.isBold = false
    run.setText(string)
}
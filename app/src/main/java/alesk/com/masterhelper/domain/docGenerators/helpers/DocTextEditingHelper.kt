package alesk.com.masterhelper.domain.docGenerators.helpers

import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFRun
import java.util.*

fun replaceText(doc: XWPFDocument, s: String, replacement: String) {
    val matches = searchMatchesJustInParagraphs(doc, s)
    matches.forEach {
        it.setText(it.getText(0).replace(s, replacement), 0)
    }

    if(matches.isEmpty()) {
        searchMatchesInTables(doc, s).forEach {
            it.setText(it.getText(0).replace(s, replacement), 0)
        }
    }
}

private fun searchMatchesJustInParagraphs(doc: XWPFDocument, s: String): List<XWPFRun> {
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

private fun searchMatchesInTables(doc: XWPFDocument, s: String): List<XWPFRun> {
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
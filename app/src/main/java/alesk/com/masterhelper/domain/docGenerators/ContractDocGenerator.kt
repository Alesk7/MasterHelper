package alesk.com.masterhelper.domain.docGenerators

import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.docGenerators.helpers.replaceText
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFTableCell
import java.util.*
import kotlin.collections.ArrayList

class ContractDocGenerator(
        val project: Project,
        val masterInfo: MasterInfo
) {

    private lateinit var doc: XWPFDocument

    fun generate(): XWPFDocument {
        doc = getContractDocument()
        fillFields()
        return doc
    }

    private fun fillFields() {
        fillContractHead()
        fillContractSubject()
        fillWorkTermsAndPossibilities()
        fillTerms()
        fillPrepayment()
        fillMasterInfo()
        fillClientInfo()
    }

    private fun fillContractHead() {
        replaceText(doc, "ContractNumber", project.contract.number.toString())
        replaceText(doc, "ContractDate",
                applicationComponent.getSimpleDateFormat().format(Date(project.contract.contractDate)))
        replaceText(doc, "City", project.address)
        replaceText(doc, "ClientName", project.client.name)
        replaceText(doc, "ClientAddress", project.client.address)
        replaceText(doc, "MasterName", masterInfo.name)
        replaceText(doc, "MasterAddress", masterInfo.address)
    }

    private fun fillContractSubject() {
        replaceText(doc, "JobsDescription", project.jobsDescription.toLowerCase())
        replaceText(doc, "ProjectAddress", project.address)
    }

    private fun fillTerms() {
        replaceText(doc, "WorkAcceptanceTerm", project.contract.workAcceptanceTerm.toString())
        replaceText(doc, "WorkAcceptanceTerm", project.contract.workAcceptanceTerm.toString())
        replaceText(doc, "GuaranteeTerm", project.contract.guaranteeTerm.toString())
        replaceText(doc, "PaymentTerm", project.contract.paymentTerm.toString())
    }

    private fun fillWorkTermsAndPossibilities() {
        replaceText(doc, "MaterialsSupplier",
                if (project.contract.isMasterMaterialsSupplier) "подрядчика" else "заказчика")
        replaceText(doc, "WorkLength", project.contract.workLength.toString())
        replaceText(doc, "WorkStartDate",
                applicationComponent.getSimpleDateFormat().format(Date(project.contract.workStartDate)))
        replaceText(doc, "WorkEndDate",
                applicationComponent.getSimpleDateFormat().format(Date(project.contract.workEndDate)))
    }

    private fun fillClientInfo() {
        replaceText(doc, "ClientName", project.client.name)
        replaceText(doc, "ClientAddress", project.client.address)
        replaceText(doc, "ClientPhone", project.client.phoneNumber)
        replaceText(doc, "ClientZip", project.client.zipcode.toString())
        if(!project.client.isOrganization){
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[0],
                    arrayOf("ClientUNP", "ClientBank", "ClientBankAddress"))
            replaceText(doc, "ClientPassport", project.client.passport)
            replaceText(doc, "ClientPassIssued", project.client.passportIssued)
            replaceText(doc, "ClientInsuranceCertificate", project.client.insuranceCertificate)
        } else {
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[0],
                    arrayOf("ClientPassport", "ClientPassIssued", "ClientInsuranceCertificate"))
            replaceText(doc, "ClientUNP", project.client.UNP.toString())
            replaceText(doc, "ClientBankAccount", project.client.bankAccount)
            replaceText(doc, "ClientBankName", project.client.bankName)
            replaceText(doc, "ClientBankCode", project.client.bankCode.toString())
            replaceText(doc, "ClientBankAddress", project.client.bankAddress)
        }
    }

    private fun fillMasterInfo() {
        replaceText(doc, "MasterName", masterInfo.name)
        replaceText(doc, "MasterAddress", masterInfo.address)
        replaceText(doc, "MasterPhone", masterInfo.phoneNumber)
        replaceText(doc, "MasterZip", masterInfo.zipcode.toString())
        if(!masterInfo.isOrganization){
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[1],
                    arrayOf("MasterUNP", "MasterBank", "MasterBankAddress"))
            replaceText(doc, "MasterPassport", masterInfo.passport)
            replaceText(doc, "MasterPassIssued", masterInfo.passportIssued)
            replaceText(doc, "MasterInsuranceCertificate", masterInfo.insuranceCertificate)
        } else {
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[1],
                    arrayOf("MasterPassport", "MasterPassIssued", "MasterInsuranceCertificate"))
            replaceText(doc, "MasterUNP", masterInfo.UNP.toString())
            replaceText(doc, "MasterBankAccount", masterInfo.bankAccount)
            replaceText(doc, "MasterBankName", masterInfo.bankName)
            replaceText(doc, "MasterBankCode", masterInfo.bankCode.toString())
            replaceText(doc, "MasterBankAddress", masterInfo.bankAddress)
        }
    }

    private fun removeRequisites(cell: XWPFTableCell, requisites: Array<String>){
        val removeIndexes: ArrayList<Int> = ArrayList()
        var requisiteIndex = 0
        cell.paragraphs.forEachIndexed { i, p ->
            p.runs.forEach {
                if(it.text().contains(requisites[requisiteIndex])) {
                    removeIndexes.add(i)
                    if(requisiteIndex < requisites.size - 1)
                        requisiteIndex ++
                }
            }
        }
        removeIndexes.forEachIndexed { index, i -> cell.removeParagraph(i - index) }
    }

    private fun fillPrepayment() {
        if(project.contract.prepayment > 0) {
            replaceText(doc, "PPS", "")
            replaceText(doc, "PPE", "")
            replaceText(doc, "Prepayment", String.format("%.2f", project.contract.prepayment))
        } else {
            doc.paragraphs.forEach { paragraph ->
                paragraph.runs.forEach {
                    val text = it.getText(0)
                    if (text != null && text.contains("PPS")) {
                        doc.removeBodyElement(doc.getPosOfParagraph(paragraph))
                        replaceText(doc, "Оставшаяся часть суммы, подлежащей", "Сумма, подлежащая")
                        return@fillPrepayment
                    }
                }
            }
        }
    }

    private fun getContractDocument(): XWPFDocument {
        val contractFile = applicationComponent.getAssets().open("contractBEL.docx")
        return XWPFDocument(contractFile)
    }

}
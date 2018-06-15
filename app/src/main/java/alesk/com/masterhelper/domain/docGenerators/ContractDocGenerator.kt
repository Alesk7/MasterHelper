package alesk.com.masterhelper.domain.docGenerators

import alesk.com.masterhelper.application.applicationComponent
import alesk.com.masterhelper.application.utils.defaultDateFormat
import alesk.com.masterhelper.data.entities.MasterInfo
import alesk.com.masterhelper.data.entities.Project
import alesk.com.masterhelper.domain.docGenerators.consts.*
import alesk.com.masterhelper.domain.docGenerators.helpers.replaceText
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFTableCell
import java.util.*
import kotlin.collections.ArrayList

class ContractDocGenerator(
        private val project: Project,
        private val masterInfo: MasterInfo
) {

    private val doc: XWPFDocument

    init{ doc = getContractDocument() }

    fun generate(): XWPFDocument {
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
        replaceText(doc, contractNumber, project.contract.number.toString())
        replaceText(doc, contractDate, defaultDateFormat.format(Date(project.contract.contractDate)))
        replaceText(doc, city, project.address)
        replaceText(doc, clientName, project.client.name)
        replaceText(doc, clientAddress, project.client.address)
        replaceText(doc, masterName, masterInfo.name)
        replaceText(doc, masterAddress, masterInfo.address)
    }

    private fun fillContractSubject() {
        replaceText(doc, jobsDescription, project.jobsDescription.toLowerCase())
        replaceText(doc, projectAddress, project.address)
    }

    private fun fillTerms() {
        repeat(2) { replaceText(doc, workAcceptanceTerm, project.contract.workAcceptanceTerm.toString()) }
        replaceText(doc, guaranteeTerm, project.contract.guaranteeTerm.toString())
        replaceText(doc, paymentTerm, project.contract.paymentTerm.toString())
    }

    private fun fillWorkTermsAndPossibilities() {
        replaceText(doc, materialsSupplier,
                if (project.contract.isMasterMaterialsSupplier) masterSupplier else clientSupplier)
        replaceText(doc, workLength, project.contract.workLength.toString())
        replaceText(doc, workStartDate, defaultDateFormat.format(Date(project.contract.workStartDate)))
        replaceText(doc, workEndDate, defaultDateFormat.format(Date(project.contract.workEndDate)))
    }

    private fun fillClientInfo() {
        replaceText(doc, clientName, project.client.name)
        replaceText(doc, clientAddress, project.client.address)
        replaceText(doc, clientPhone, project.client.phoneNumber)
        replaceText(doc, clientZip, project.client.zipcode.toString())
        if(!project.client.isOrganization){
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[0],
                    arrayOf(clientUNP, "ClientBank", clientBankAddress))
            replaceText(doc, clientPassport, project.client.passport)
            replaceText(doc, clientPassportIssued, project.client.passportIssued)
            replaceText(doc, clientInsuranceCertificate, project.client.insuranceCertificate)
        } else {
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[0],
                    arrayOf(clientPassport, clientPassportIssued, clientInsuranceCertificate))
            replaceText(doc, clientUNP, project.client.UNP.toString())
            replaceText(doc, clientBankAccount, project.client.bankAccount)
            replaceText(doc, clientBankName, project.client.bankName)
            replaceText(doc, clientBankCode, project.client.bankCode.toString())
            replaceText(doc, clientBankAddress, project.client.bankAddress)
        }
    }

    private fun fillMasterInfo() {
        replaceText(doc, masterName, masterInfo.name)
        replaceText(doc, masterAddress, masterInfo.address)
        replaceText(doc, masterPhone, masterInfo.phoneNumber)
        replaceText(doc, masterZip, masterInfo.zipcode.toString())
        if(!masterInfo.isOrganization){
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[1],
                    arrayOf(masterUNP, "MasterBank", masterBankAddress))
            replaceText(doc, masterPassport, masterInfo.passport)
            replaceText(doc, masterPassportIssued, masterInfo.passportIssued)
            replaceText(doc, masterInsuranceCertificate, masterInfo.insuranceCertificate)
        } else {
            removeRequisites(doc.tables[doc.tables.size - 1].rows[0].tableCells[1],
                    arrayOf(masterPassport, masterPassportIssued, masterInsuranceCertificate))
            replaceText(doc, masterUNP, masterInfo.UNP.toString())
            replaceText(doc, masterBankAccount, masterInfo.bankAccount)
            replaceText(doc, masterBankName, masterInfo.bankName)
            replaceText(doc, masterBankCode, masterInfo.bankCode.toString())
            replaceText(doc, masterBankAddress, masterInfo.bankAddress)
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
            replaceText(doc, prepaymentStartParagaph, "")
            replaceText(doc, prepaymentEndParagraph, "")
            replaceText(doc, prepayment, String.format("%.2f", project.contract.prepayment))
        } else {
            doc.paragraphs.forEach { paragraph ->
                paragraph.runs.forEach {
                    val text = it.getText(0)
                    if (text != null && text.contains(prepaymentStartParagaph)) {
                        doc.removeBodyElement(doc.getPosOfParagraph(paragraph))
                        replaceText(doc, "Оставшаяся часть суммы, подлежащей", "Сумма, подлежащая")
                        return@fillPrepayment
                    }
                }
            }
        }
    }

    private fun getContractDocument(): XWPFDocument {
        val contractFile = applicationComponent.getAssets().open(contractFileName)
        return XWPFDocument(contractFile)
    }

}
package com.example.autobank.service

import com.example.autobank.data.models.Attachment
import com.example.autobank.data.models.Card
import com.example.autobank.data.models.Payment
import com.example.autobank.data.models.ReceiptInfo
import com.example.autobank.data.receipt.*
import com.example.autobank.repository.receipt.CardRepository
import com.example.autobank.repository.receipt.PaymentRepository
import com.example.autobank.repository.receipt.ReceiptInfoViewRepository
import com.example.autobank.repository.receipt.ReceiptRepository
import com.example.autobank.repository.receipt.specification.ReceiptInfoSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort

@Service

class ReceiptService {

    @Autowired
    lateinit var onlineUserService: OnlineUserService

    @Autowired
    lateinit var receiptRepository: ReceiptRepository

    @Autowired
    lateinit var blobService: BlobService

    @Autowired
    lateinit var attachmentService: AttachmentService

    @Autowired
    lateinit var cardRepository: CardRepository

    @Autowired
    lateinit var paymentRepository: PaymentRepository

    @Autowired
    lateinit var receiptInfoViewRepository: ReceiptInfoViewRepository

    @Autowired
    lateinit var paymentCardService: PaymentCardService



    fun createReceipt(receiptRequestBody: ReceiptRequestBody): ReceiptResponseBody {
        val user = onlineUserService.getOnlineUser() ?: throw Exception("User not found")
        val receiptinfo = receiptRequestBody.receipt ?: throw Exception("Receipt not sent")
        receiptinfo.onlineUserId = user.id;

        val storedReceipt = receiptRepository.save(receiptinfo);


        /**
         * Save attachments
         */

        val attachments = receiptRequestBody.attachments
        val attachmentsnames = mutableListOf<String>()
        try {
            attachments.forEach { attachment ->
                val imgname = blobService.uploadFile(attachment)
                attachmentService.createAttachment(Attachment(0, storedReceipt.id, imgname))
                println("Attachent name: $imgname")
                attachmentsnames.add(imgname)
            }
        } catch (e: Exception) {
            receiptRepository.delete(storedReceipt)
            throw e;
        }

        /**
         * Save payment information
         */
        if (receiptRequestBody.receiptPaymentInformation != null && receiptRequestBody.receiptPaymentInformation.usedOnlineCard && receiptRequestBody.receiptPaymentInformation.cardnumber != null && receiptRequestBody.receiptPaymentInformation.cardnumber != "") {
            val card = Card(0, storedReceipt.id, receiptRequestBody.receiptPaymentInformation.cardnumber)
            cardRepository.save(card)
        } else if (receiptRequestBody.receiptPaymentInformation != null && !receiptRequestBody.receiptPaymentInformation.usedOnlineCard && receiptRequestBody.receiptPaymentInformation.accountnumber != null && receiptRequestBody.receiptPaymentInformation.accountnumber != "") {
            val payment = Payment(0, storedReceipt.id, receiptRequestBody.receiptPaymentInformation.accountnumber)
            paymentRepository.save(payment)
        } else {
            throw Exception("Payment information not sent")
        }

        storedReceipt.onlineUserId = null

        val response = ReceiptResponseBody()
        response.receipt = storedReceipt
        response.attachments = attachmentsnames.toTypedArray()
        response.receiptPaymentInformation = receiptRequestBody.receiptPaymentInformation

        return response

    }

    fun getAllReceiptsFromUser(from: Int, count: Int, status: String?, committeeName: String?, search: String?, sortField: String?, sortOrder: String?): ReceiptListResponseBody? {


        val user = onlineUserService.getOnlineUser() ?: throw Exception("User not found")


        val sort = if (!sortField.isNullOrEmpty()) {
            Sort.by(Sort.Direction.fromString(sortOrder ?: "ASC"), sortField)
        } else {
            Sort.by(Sort.Direction.DESC, "receiptCreatedAt")
        }
        val pageable = PageRequest.of(from, count, sort)

        val specification = ReceiptInfoSpecification(user.id, status, committeeName, search)

        val page: List<ReceiptInfo> = receiptInfoViewRepository.findAll(specification, pageable).toList()
        val total: Long = receiptInfoViewRepository.count(specification)

        return ReceiptListResponseBody(page.toTypedArray(), total)
    }

    fun getReceipt(id: Int): CompleteReceipt? {
        val user = onlineUserService.getOnlineUser() ?: throw Exception("User not found")
        val receipt = receiptInfoViewRepository.findByReceiptId(id)
        if (receipt == null || receipt.userId != user.id) {
            return null
        }

        return getCompleteReceipt(receipt)





    }

    fun getCompleteReceipt(receipt: ReceiptInfo): CompleteReceipt {
        var card = Card(0, 0, "")
        var payment = Payment(0, 0, "")

        if (receipt.paymentOrCard == "Card") {
            card = paymentCardService.getCardByReceiptId(receipt.receiptId)
        } else if (receipt.paymentOrCard == "Payment") {
            payment = paymentCardService.getPaymentByReceiptId(receipt.receiptId)
        }
        println(receipt.paymentOrCard)

        // Get files
        val attachments = attachmentService.getAttachmentsByReceiptId(receipt.receiptId)
        val files = attachments.map { attachment -> attachment.name.split(".")[1]+"."+blobService.downloadImage(attachment.name) }

        return CompleteReceipt(
            receipt.receiptId,
            receipt.amount,
            receipt.receiptName,
            receipt.receiptDescription,
            receipt.receiptCreatedAt,
            receipt.committeeName,
            receipt.userFullname,
            receipt.paymentOrCard,
            receipt.attachmentCount,
            receipt.latestReviewStatus,
            receipt.latestReviewCreatedAt,
            receipt.latestReviewComment,
            payment.account_number,
            card.card_number,
            files
        )


    }


}
package com.example.autobank.data.receipt

import com.example.autobank.data.models.Receipt

data class ReceiptDTO(
    val id: Int? = null,
    val amount: Double? = null,
    val name: String? = null,
    val description: String? = null,
    val createdAt: String? = null,
    val committee_id: Int? = null,
    val onlineUserId: Int? = null
)

class ReceiptRequestBody {
    val receipt: ReceiptDTO? = null
    val receiptPaymentInformation: ReceiptPaymentInformation? = null
    val attachments: Array<String> = arrayOf()
}
package com.example.autobank.service

import com.example.autobank.data.Economicrequest
import com.example.autobank.data.Receipt
import com.example.autobank.repository.ReceiptRepository
import org.springframework.stereotype.Service

@Service
class ReceiptService(private val repository: ReceiptRepository) {

    fun createReceipt(receipt: Receipt) {
        repository.save(receipt)
    }
}

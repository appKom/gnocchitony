package com.example.autobank.service

import com.example.autobank.data.Economicrequest
import com.example.autobank.data.Receipt
import com.example.autobank.repository.EconomicrequestRepository
import com.example.autobank.repository.ReceiptRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReceiptService {

    @Autowired
    lateinit var receiptRepository: ReceiptRepository

    fun createReceipt(receipt: Receipt) {
        receiptRepository.save(receipt)
    }

    fun getAllReceipts() : List<Receipt> {
        return receiptRepository.findAll();
    }

}
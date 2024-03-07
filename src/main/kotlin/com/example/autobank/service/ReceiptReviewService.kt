package com.example.autobank.service

import com.example.autobank.data.Receipt
import com.example.autobank.data.ReceiptReview
import com.example.autobank.data.user.OnlineUser
import com.example.autobank.repository.ReceiptRepository
import com.example.autobank.repository.ReceiptReviewRepository
import jakarta.transaction.Status
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReceiptReviewService(private val repository: ReceiptReviewRepository) {

    fun checkReceiptReview(receipt: Receipt) : Boolean{
        return repository.existsByRecieptId(receipt.id)
    }

    fun getReceiptReview(receipt: Receipt): ReceiptReview{
        return repository.getByRecieptId(receipt.id)
    }

    fun createReceiptReview(status: Boolean, user: OnlineUser, description: String, receipt: Receipt){
        val dato = LocalDateTime.now()
        val receiptReview = ReceiptReview( receiptReviewId = -1, recieptId = receipt.id, date = dato, adminID = user.onlineId, description = description, status = status )
        repository.save(receiptReview)
    }

    fun updateReceiptReviewByStatus(receiptReview: ReceiptReview, status: Boolean){
        val receiptReviewOptional = repository.findById(receiptReview.receiptReviewId)
        if (receiptReviewOptional.isPresent){
            val receiptReview = receiptReviewOptional.get()
            receiptReview.status = status
            repository.save(receiptReview)
        }
    }

}
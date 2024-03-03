package com.example.autobank.service

import com.example.autobank.data.Receipt
import com.example.autobank.data.ReceiptReview
import com.example.autobank.data.user.OnlineUser
import com.example.autobank.repository.ReceiptRepository
import com.example.autobank.repository.ReceiptReviewRepository
import jakarta.transaction.Status
import org.springframework.stereotype.Service

@Service
class ReceiptReviewService(private val repository: ReceiptReviewRepository) {

    fun checkReceiptReview(receipt: Receipt) : Boolean{
        return repository.existsByRecieptId(receipt.id)
    }

    fun getReceiptReview(receipt: Receipt): ReceiptReview{
        return repository.getByRecieptId(receipt.id)
    }

    fun createReceiptReview(status: Boolean, user: OnlineUser, description: String, receipt: Receipt){
        // val receiptReview = ReceiptReview( recieptId = receipt.id, date = "2017-01-13T17:09:42.4100000", d )
        //repository.save(receiptReview)
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
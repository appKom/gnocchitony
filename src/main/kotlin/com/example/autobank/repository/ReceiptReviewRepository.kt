package com.example.autobank.repository

import com.example.autobank.data.ReceiptReview
import org.springframework.data.jpa.repository.JpaRepository

interface ReceiptReviewRepository : JpaRepository<ReceiptReview, Int> {

    fun existsByRecieptId(recieptId : Int): Boolean

    fun getByRecieptId(recieptId: Int): ReceiptReview


}
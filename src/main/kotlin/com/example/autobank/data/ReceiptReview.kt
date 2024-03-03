package com.example.autobank.data

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "receiptReview")
class ReceiptReview (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "receiptReviewId", nullable = false)
        val receiptReviewId: Int,

        @Column(name = "recieptId", nullable = false)
        val recieptId: Int,

        @Column(name = "date", nullable = false)
        val date: LocalDateTime,

        @Column(name = "adminId", nullable = false)
        val adminID: String,

        @Column(name = "description", nullable = true)
        val description: String?,

        @Column(name = "status", nullable = true)
        var status: Boolean?

)
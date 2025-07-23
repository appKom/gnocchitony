package com.example.autobank.data.models

import com.example.autobank.data.user.OnlineUser
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

enum class ReceiptStatus {
    APPROVED, DENIED
}

@Entity
@Table(name = "receiptreview")
class ReceiptReview (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @NotNull
    val id: String,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    val receipt: Receipt,


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: ReceiptStatus,

    @Column(name = "comment")
    val comment: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onlineuser_id")
    val user: OnlineUser,


    @CreationTimestamp
    @Column(name = "createdat")
    val createdat: LocalDateTime?,
    )

package com.example.autobank.data.models



import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

enum class EconomicRequestStatus {
    APPROVED, DENIED
}

@Entity
@Table(name = "economicrequestreview")
class EconomicRequestReview (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    val id: Int,

    @Column(name = "economicrequest_id")
    val economicrequestId: Int,


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: EconomicRequestStatus,

    @Column(name = "comment")
    val comment: String,

    @Column(name = "onlineuser_id")
    var onlineUserId: Int,

    @CreationTimestamp
    @Column(name = "createdat")
    val createdat: LocalDateTime?,
)

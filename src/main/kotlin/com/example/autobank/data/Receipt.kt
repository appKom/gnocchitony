package com.example.autobank.data

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "receipt")
class Receipt (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int,

    @Column(name = "amount", nullable = false)
    val amount: BigDecimal,

    @Column(name = "comitee_id", nullable = false)
    val comitee_id: Int,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "description", nullable = true)
    val description: String?,

    @Column(name = "onlineuser_id", nullable = true)
    val onlineuser_id: String?




)


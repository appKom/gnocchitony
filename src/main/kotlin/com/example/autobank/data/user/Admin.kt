package com.example.autobank.data.user

import jakarta.persistence.*


//@Entity
//@Table(name = "admin")
class Admin(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: String,

    onlineId: String,
    email: String
)
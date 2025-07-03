package com.example.autobank.data.user

import jakarta.persistence.*


//@Entity
//@Table(name = "regularuser")
class RegularUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: String,
    onlineId: String,
    email: String
)
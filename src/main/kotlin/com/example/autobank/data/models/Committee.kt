package com.example.autobank.data.models

import jakarta.persistence.*

@Entity
@Table(name = "committee")
class Committee (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String,
    val name: String
)
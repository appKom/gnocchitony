package com.example.autobank.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Reciept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var amount: Double = 0.0
    var comitee_id: Int = 0
    var name: String = ""
    var description: String = ""
    var onlineuser_id: Int = 0

}

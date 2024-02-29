package com.example.autobank.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


/*
@Entity
 class Economicrequest {

    @Id
    var id: Int = 0
    var subject: String = ""
    var purpose: String = ""
    var date: String = ""
    var duration: String = ""
    var description: String = ""
    var amount: Double = 0.0
    var personcount: Int = 0
    var names: String = ""
    var paymentdescription: String = ""
    var otherinformation: String = ""
    var onlineuser_id: Int = 0
    var status: String = ""

    var processedByUserId: Int = 0

    constructor() {
    }

}*/

@Entity
data class Economicrequest(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int, val subject: String, val purpose: String, val date: String, val duration: String, val description: String, val amount: Double, val personcount: Int, val names: String, val paymentdescription: String, val otherinformation: String, val onlineuser_id: String) {
}
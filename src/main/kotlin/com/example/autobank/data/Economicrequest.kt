package com.example.autobank.data

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id



@Entity
data class Economicrequest(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int, val subject: String, val purpose: String, val date: String, val duration: String, val description: String, val amount: Double, val personcount: Int, val names: String, val paymentdescription: String, val otherinformation: String, val onlineuser_id: String) {
}
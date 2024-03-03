package com.example.autobank.repository

import com.example.autobank.data.Economicrequest
import com.example.autobank.data.Receipt
import org.springframework.data.jpa.repository.JpaRepository

interface ReceiptRepository : JpaRepository<Receipt, Int> {

}


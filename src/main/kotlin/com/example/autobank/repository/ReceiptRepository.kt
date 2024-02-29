package com.example.autobank.repository;

import com.example.autobank.data.Receipt
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository

@Repository
interface ReceiptRepository : JpaRepository<Receipt, Int> {




}

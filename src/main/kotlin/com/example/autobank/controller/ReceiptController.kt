package com.example.autobank.controller

import com.example.autobank.data.Receipt
import com.example.autobank.service.ReceiptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/receipt")
class ReceiptController() {

    @Autowired
    lateinit var receiptService: ReceiptService;

    @PostMapping("/create")
    fun createReceipt(@RequestBody receipt: Receipt) : ResponseEntity<String> {
        try {
            receiptService.createReceipt(receipt);
            return ResponseEntity.ok("Economic request created");
        } catch (e: Exception) {
            return ResponseEntity.status(500).body("Error creating economic request");
        }
    }

    @GetMapping("/getall")
    fun getAllReceipts() : ResponseEntity<List<Receipt>> {
        try {
            return ResponseEntity.ok(receiptService.getAllReceipts());
        } catch (e: Exception) {
            return ResponseEntity.status(500).body(emptyList());
        }
    }

}
package com.example.autobank.controller

import com.example.autobank.data.Economicrequest
import com.example.autobank.data.Receipt
import com.example.autobank.data.user.OnlineUser
import com.example.autobank.service.AdminService
import com.example.autobank.service.EconomicrequestService
import com.example.autobank.service.ReceiptReviewService
import com.example.autobank.service.ReceiptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(@Autowired val adminService: AdminService, @Autowired val economicrequest: EconomicrequestService, @Autowired val receiptService: ReceiptService,  @Autowired val receiptReviewService: ReceiptReviewService) {
    @GetMapping("/all")
    fun getAdmins(): ResponseEntity<Any>{
        val admins = adminService.getAdmins()
        return ResponseEntity.ok(admins)
    }

    @GetMapping("/check")
    fun checkAdmin(user: OnlineUser): Boolean {
        return adminService.checkIfAdmin(user)
    }

    @PostMapping("/request")
    fun checkData(@RequestBody request: Economicrequest) {
        return economicrequest.createEconomicrequest(request)
    }

    @PostMapping("/receipt")
    fun putReceipt(@RequestBody receipt: Receipt) {
        return receiptService.createReceipt(receipt)
    }

    data class ApproveReceiptBody(
            val receipt: Receipt,
            val user: OnlineUser,
            val status: Boolean,
            val description: String
    )

    @PutMapping("/approveReceipt")
    fun approveReceipt(@RequestBody approveReceiptBody : ApproveReceiptBody ) {
        if (checkAdmin(approveReceiptBody.user)){
            if (receiptService.checkReceipt(approveReceiptBody.receipt)) {
                if (receiptReviewService.checkReceiptReview(approveReceiptBody.receipt)) {
                    val receiptReview = receiptReviewService.getReceiptReview(approveReceiptBody.receipt)
                    receiptReviewService.updateReceiptReviewByStatus(receiptReview, approveReceiptBody.status)
                }else {
                    receiptReviewService.addReceiptReview()
                }
            }
        }

    }



}
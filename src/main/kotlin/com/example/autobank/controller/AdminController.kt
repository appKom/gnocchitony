package com.example.autobank.controller

import com.example.autobank.data.Economicrequest
import com.example.autobank.data.user.OnlineUser
import com.example.autobank.service.AdminService
import com.example.autobank.service.AuthService
import com.example.autobank.service.EconomicRequestReviewService
import com.example.autobank.service.EconomicrequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(@Autowired val authService: AuthService, @Autowired val adminService: AdminService, @Autowired val economicrequest: EconomicrequestService, @Autowired val economicRequestReviewService: EconomicRequestReviewService) {

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
    fun saveRequest(@RequestBody request: Economicrequest) {
        return economicrequest.createEconomicrequest(request)
    }

    data class ApproveEconomicRequestBody(
            val economicrequest: Economicrequest,
            val user: OnlineUser,
            val status: Boolean,
            val description: String
    )

    @PutMapping("/request")
    fun approveRequest(@RequestBody requestBody: ApproveEconomicRequestBody){
        if (adminService.checkIfAdmin(requestBody.user)){
            if (economicRequestReviewService.checkIfRequest(requestBody.economicrequest)){
                val review = economicRequestReviewService.getReview(requestBody.economicrequest)
                economicRequestReviewService.updateStatus(review, requestBody.status)
            }
            else {
                economicRequestReviewService.createEconomicrequestReview(requestBody.status, requestBody.user, requestBody.description, requestBody.economicrequest)
            }
        }
    }

}
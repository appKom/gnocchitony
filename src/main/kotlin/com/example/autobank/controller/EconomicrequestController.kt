package com.example.autobank.controller

import com.example.autobank.data.Economicrequest
import com.example.autobank.service.EconomicrequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/economicrequest")
class EconomicrequestController() {

    @Autowired
    lateinit var economicrequestService: EconomicrequestService;

    @PostMapping("/create")
    fun createEconomicrequest(@RequestBody economicrequest: Economicrequest) : ResponseEntity<String> {
        try {
            economicrequestService.createEconomicrequest(economicrequest);
            return ResponseEntity.ok("Economic request created");
        } catch (e: Exception) {
            return ResponseEntity.status(500).body("Error creating economic request");
        }
    }

    @GetMapping("/getall")
    fun getAllEconomicrequests() : ResponseEntity<List<Economicrequest>> {
        try {
            return ResponseEntity.ok(economicrequestService.getAllEconomicrequests());
        } catch (e: Exception) {
            return ResponseEntity.status(500).body(emptyList());
        }
    }

}
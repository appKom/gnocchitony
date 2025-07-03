package com.example.autobank.controller

import com.example.autobank.data.models.Economicrequest
import com.example.autobank.service.AuthenticationService
import com.example.autobank.service.EconomicrequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/economicrequest")
class EconomicrequestController() {
    @Autowired
    lateinit var economicrequestService: EconomicrequestService;

    @Autowired
    lateinit var authenticationService: AuthenticationService;

    //@PostMapping("/create")
    fun createEconomicrequest(@RequestBody economicrequest: Economicrequest): ResponseEntity<Economicrequest> {
        return try {
            val created: Economicrequest = economicrequestService.createEconomicrequest(economicrequest);
            created.onlineUserId = null;
            ResponseEntity.ok(created);
        } catch (e: Exception) {
            ResponseEntity.badRequest().build();
        }
    }

    //@GetMapping("/get/{id}")
    fun getEconomicrequest(@PathVariable id: Int): Economicrequest {
        return economicrequestService.getEconomicrequest(id);
    }

}
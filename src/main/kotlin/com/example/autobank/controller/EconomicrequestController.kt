package com.example.autobank.controller

import com.example.autobank.data.Economicrequest
import com.example.autobank.service.EconomicrequestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/economicrequest")
class EconomicrequestController() {

    @Autowired
    lateinit var economicrequestService: EconomicrequestService;

    @PostMapping("/create")
    fun createEconomicrequest(@RequestBody economicrequest: Economicrequest) {
        economicrequestService.createEconomicrequest(economicrequest);
    }

    @GetMapping("/getall")
    fun getAllEconomicrequests() : List<Economicrequest> {
        return economicrequestService.getAllEconomicrequests();
    }

}
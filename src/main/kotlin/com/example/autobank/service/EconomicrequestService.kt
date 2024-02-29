package com.example.autobank.service

import com.example.autobank.data.Economicrequest
import com.example.autobank.repository.EconomicrequestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EconomicrequestService {

    @Autowired
    lateinit var economicrequestRepository: EconomicrequestRepository

    fun createEconomicrequest(economicrequest: Economicrequest) {
        economicrequestRepository.save(economicrequest)
    }

    fun getAllEconomicrequests() : List<Economicrequest> {
        return economicrequestRepository.findAll();
    }

}
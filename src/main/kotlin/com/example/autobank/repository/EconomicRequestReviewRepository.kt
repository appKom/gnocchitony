package com.example.autobank.repository

import com.example.autobank.data.EconomicRequestReview
import jakarta.transaction.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EconomicRequestReviewRepository : JpaRepository<EconomicRequestReview, Int> {
    fun existsByEconomicRequestId(id : Int) : Boolean


    @Query("update EconomicRequestReview e set e.status = ?2 where e.economicRequestId = ?1")
    fun findEconomicRequestReviewsEconomicRequestIdAndUpdateStatus(id: Int, status: Boolean)

    fun getEconomicRequestReviewByEconomicRequestId(id: Int) : EconomicRequestReview
}

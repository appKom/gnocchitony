package com.example.autobank.controller

import com.example.autobank.data.models.ReceiptInfo
import com.example.autobank.data.receipt.*
import com.example.autobank.service.ImageService
import com.example.autobank.service.ReceiptService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/receipt")
class ReceiptController {

    @Autowired
    lateinit var imageService: ImageService

    @Autowired
    lateinit var receiptService: ReceiptService

    @PostMapping("/create")
    fun createReceipt(@RequestBody receipt: ReceiptRequestBody): ResponseEntity<ReceiptResponseBody> {
        return try {
            val res = receiptService.createReceipt(receipt)
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            println(e)
            ResponseEntity.badRequest().build()
        }

    }

    /* For testing. temporary */
    @GetMapping("/getimage/{id}")
    fun getReceipt(@PathVariable id: String): String {
        return imageService.downloadImage(id)
    }
    /*  */

    @GetMapping("/getall")
    fun getAllReceipts(@Param("page") from: Int = 0, @Param("count") count: Int = 10, @Param("status") status: String?, @Param("committee") committee: String?, @Param("search") search: String?, @Param("sortOrder") sortOrder: String?, @Param("sortField") sortField: String?): ResponseEntity<ReceiptListResponseBody> {
        return try {
            val res = receiptService.getAllReceiptsFromUser(from, count, status, committee, search, sortField, sortOrder)
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping("/get/{id}")
    fun getReceipt(@PathVariable id: Int): ResponseEntity<CompleteReceipt> {
        return try {
            val res = receiptService.getReceipt(id)
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity.badRequest().build()
        }
    }


}
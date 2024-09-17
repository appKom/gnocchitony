package com.example.autobank.repository

import com.example.autobank.data.receipt.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AttachmentRepository : JpaRepository<Attachment, Int> {

}
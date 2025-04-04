package com.example.autobank.controller


import com.example.autobank.data.authentication.AuthenticatedUserResponse
import com.example.autobank.service.AuthenticationService
import com.example.autobank.service.OnlineUserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseCookie
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/auth")
class AuthenticationController {
    @Autowired
    lateinit var onlineUserService: OnlineUserService;

    @GetMapping("/getuser")
    fun checkUser(): ResponseEntity<AuthenticatedUserResponse> {
            return try {
                ResponseEntity.ok().body(onlineUserService.checkUser())
            } catch (e: Exception) {
                print(e)
                ResponseEntity.badRequest().build();
            }
        }
    
}
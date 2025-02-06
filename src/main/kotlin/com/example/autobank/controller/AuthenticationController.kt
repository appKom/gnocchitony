package com.example.autobank.controller


import com.example.autobank.data.authentication.AuthenticatedUserResponse
import com.example.autobank.service.AuthenticationService
import com.example.autobank.service.OnlineUserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.http.ResponseCookie



@RestController
@RequestMapping("/api/auth")
class AuthenticationController {
    @Autowired
    lateinit var onlineUserService: OnlineUserService;


    @Autowired
    lateinit var authenticationService: AuthenticationService;

    @GetMapping("/setuser")
    fun setCookie(@RequestHeader("Authorization") authHeader: String): ResponseEntity<Any> {
        val token = authHeader.replace("Bearer ", "") // Extract token from the header

        val cookie = ResponseCookie.from("access_token", token)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(12 * 60 * 60)
            .build()

        return ResponseEntity.ok()
            .header("Set-Cookie", cookie.toString())
            .body(mapOf("success" to true))
    }

    @GetMapping("/getuser")
    fun checkUser(): ResponseEntity<AuthenticatedUserResponse> {
        println("Checking user")

            return try {
                ResponseEntity.ok(onlineUserService.checkUser())
            } catch (e: Exception) {
                print(e)
                ResponseEntity.badRequest().build();
            }
        }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<Any> {
        val cookie = ResponseCookie.from("access_token", "")
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(0)
            .build()

        return ResponseEntity.ok()
            .header("Set-Cookie", cookie.toString())
            .body(mapOf("success" to true))
    }
    
}
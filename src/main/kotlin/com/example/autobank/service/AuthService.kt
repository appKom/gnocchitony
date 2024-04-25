package com.example.autobank.service

import com.example.autobank.data.Economicrequest
import com.example.autobank.data.user.OnlineUser
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class AuthService (@Value("\${auth0.domain}") val domain: String) {

    data class Auth0User(
        val sub: String,
        val name: String,
        val email: String,
    )



    fun getUserinfo(accessToken: String): Auth0User {
        val url = "$domain/userinfo"
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Authorization", "Bearer $accessToken")
            .GET()
            .build()

        val client = HttpClient.newHttpClient()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonNode = ObjectMapper().readTree(response.body())
        
        return Auth0User(
            sub = jsonNode["sub"].asText(),
            name = jsonNode["name"].asText(),
            email = jsonNode["email"].asText()
        )
    }
}
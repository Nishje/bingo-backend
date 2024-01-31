package com.example.bingobackend.controller

import com.example.bingobackend.service.UserService
import com.example.bingobackend.controller.dto.UserDTO
import com.example.bingobackend.provider.JwtTokenProvider
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService, private val jwtTokenProvider: JwtTokenProvider
) {

    @PostMapping("/register")
    fun register(@RequestBody registerBody: UserDTO) {
        userService.register(registerBody)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginBody: UserDTO, response: HttpServletResponse): ResponseEntity<Map<String, String>> {
        val id = userService.login(loginBody)
        val jwt = jwtTokenProvider.generateToken(id)
        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)
        return ResponseEntity.ok(mapOf(Pair("message", "Successful")))
    }
}
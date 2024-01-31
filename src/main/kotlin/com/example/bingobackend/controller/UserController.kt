package com.example.bingobackend.controller

import com.example.bingobackend.service.UserService
import com.example.bingobackend.controller.dto.UserDTO
import com.example.bingobackend.provider.JwtTokenProvider
import com.example.bingobackend.util.ResponseType
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class UserController(
    private val userService: UserService, private val jwtTokenProvider: JwtTokenProvider
) {

    @PostMapping("/register")
    fun register(@RequestBody registerBody: UserDTO): ResponseEntity<ResponseType<String>> {
        userService.register(registerBody)
        return ResponseEntity.ok(ResponseType.SuccessMessage())
    }

    @PostMapping("/login")
    fun login(@RequestBody loginBody: UserDTO, response: HttpServletResponse): ResponseEntity<ResponseType<String>> {
        val id = userService.login(loginBody)
        addJwtCookie(id, response)
        return ResponseEntity.ok(ResponseType.SuccessMessage())
    }

    private fun addJwtCookie(id: UUID, response: HttpServletResponse) {
        val jwt = jwtTokenProvider.generateToken(id)
        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)
    }
}
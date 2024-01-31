package com.example.bingobackend.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordService(private val encoder: BCryptPasswordEncoder = BCryptPasswordEncoder()) {
    fun verify(rawPassword: String, hashedPassword: String): Boolean {
        return encoder.matches(rawPassword, hashedPassword)
    }

    fun hash(password: String): String {
        return encoder.encode(password)
    }
}
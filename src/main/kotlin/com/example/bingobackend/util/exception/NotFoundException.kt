package com.example.bingobackend.util.exception

class NotFoundException(private val entity: String) : RuntimeException(entity) {
    override val message: String
        get() = "$entity not found"
}
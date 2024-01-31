package com.example.bingobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BingoBackendApplication

fun main(args: Array<String>) {
    runApplication<BingoBackendApplication>(*args)
}

package com.example.bingobackend.data.entity

import jakarta.persistence.*

@Entity
data class BingoCell(
    @EmbeddedId
    val bingoCellId: BingoCellId,
    val content: String,
    val isChecked: Boolean
)

package com.example.bingobackend.controller.dto

import java.util.UUID

data class CardDTO(
    val id: UUID?,
    val title: String,
    val b: ColumnDTO,
    val i: ColumnDTO,
    val n: ColumnDTO,
    val g: ColumnDTO,
    val o: ColumnDTO
)

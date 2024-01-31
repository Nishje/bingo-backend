package com.example.bingobackend.controller.dto

import java.util.UUID

data class CardDTO(
    val id: UUID?,
    val title: String,
    val bColumn: ColumnDTO,
    val iColumn: ColumnDTO,
    val nColumn: ColumnDTO,
    val gColumn: ColumnDTO,
    val oColumn: ColumnDTO
)

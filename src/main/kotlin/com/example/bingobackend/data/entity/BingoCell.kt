package com.example.bingobackend.data.entity

import jakarta.persistence.*
import org.hibernate.validator.constraints.Range
import org.springframework.beans.factory.annotation.Value

@Entity
data class BingoCell(
    @EmbeddedId
    val bingoCellId: BingoCellId,
    val content: String,
    val isChecked: Boolean
)

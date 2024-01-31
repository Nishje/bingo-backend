package com.example.bingobackend.data.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.util.UUID

@Entity
data class BingoCard(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val cardId: UUID,
    val title: String,
    @OneToMany(mappedBy = "bingoCellId.card", cascade = [CascadeType.REMOVE])
    val cells: Collection<BingoCell>
)

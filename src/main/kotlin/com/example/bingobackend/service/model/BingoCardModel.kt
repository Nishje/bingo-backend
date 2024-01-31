package com.example.bingobackend.service.model

import java.util.UUID

class BingoCardModel(
    val id: UUID,
    val title: String,
    val b: Iterable<BingoCellModel>,
    val i: Iterable<BingoCellModel>,
    val n: Iterable<BingoCellModel>,
    val g: Iterable<BingoCellModel>,
    val o: Iterable<BingoCellModel>
)
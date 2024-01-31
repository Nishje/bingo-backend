package com.example.bingobackend.data

import com.example.bingobackend.data.entity.BingoCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BingoCardRepository : JpaRepository<BingoCard, UUID> {}
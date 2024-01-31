package com.example.bingobackend.data

import com.example.bingobackend.data.entity.BingoCell
import com.example.bingobackend.data.entity.BingoCellId
import org.springframework.data.jpa.repository.JpaRepository

interface BingoCellRepository : JpaRepository<BingoCell, BingoCellId>
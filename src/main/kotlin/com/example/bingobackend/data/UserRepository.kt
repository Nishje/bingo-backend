package com.example.bingobackend.data

import com.example.bingobackend.data.entity.BingoCard
import com.example.bingobackend.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {
    fun existsByUserName(userName: String): Boolean
    fun findByUserName(userName: String): User?
//    fun updateUserByCards(user: User, cards: MutableCollection<BingoCard>)
}
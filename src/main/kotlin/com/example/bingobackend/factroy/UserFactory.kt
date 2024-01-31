package com.example.bingobackend.factroy

import com.example.bingobackend.service.model.UserModel
import com.example.bingobackend.controller.dto.UserDTO
import com.example.bingobackend.data.entity.BingoCard
import com.example.bingobackend.data.entity.User
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserFactory {

    fun createBusinessModel(dto: UserDTO, encodedPassword: String): UserModel {
        return UserModel(UUID.randomUUID(), dto.userName, encodedPassword)
    }

    fun createBusinessModel(entity: User): UserModel {
        return UserModel(entity.id, entity.userName, entity.password)
    }

    fun createEntityModel(user: UserModel): User {
        return User(user.id, user.userName, user.password, emptyList<BingoCard>().toMutableList())
    }

}
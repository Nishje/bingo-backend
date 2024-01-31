package com.example.bingobackend.service

import com.example.bingobackend.factroy.UserFactory
import com.example.bingobackend.service.model.UserModel
import com.example.bingobackend.controller.dto.UserDTO
import com.example.bingobackend.data.UserRepository
import com.example.bingobackend.util.exception.InvalidCredentialsException
import com.example.bingobackend.util.exception.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.UUID

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val userFactory: UserFactory,
    private val passwordEncoder: PasswordService
) {
    fun register(user: UserDTO) {
        val password = passwordEncoder.hash(user.password)
        createUser(userFactory.createBusinessModel(user, password))
    }

    fun login(user: UserDTO): UUID {
        if (!userRepository.existsByUserName(user.userName)) throw NotFoundException("User")
        val userModel = getUser(user.userName)
        if (passwordEncoder.verify(user.password, userModel.password)) return userModel.id
        else throw InvalidCredentialsException()
    }

    private fun createUser(user: UserModel): UserModel {
        return userFactory.createBusinessModel(userRepository.save(userFactory.createEntityModel(user)))
    }

    private fun getUser(userName: String): UserModel {
        return userFactory.createBusinessModel(
            userRepository.findByUserName(userName).orElseThrow { NotFoundException("User") })
    }
}
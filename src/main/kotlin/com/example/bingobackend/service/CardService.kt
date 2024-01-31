package com.example.bingobackend.service

import com.example.bingobackend.factroy.CardFactory
import com.example.bingobackend.controller.dto.CardDTO
import com.example.bingobackend.data.BingoCardRepository
import com.example.bingobackend.data.BingoCellRepository
import com.example.bingobackend.data.UserRepository
import com.example.bingobackend.util.exception.NotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CardService(
    private val bingoCardRepository: BingoCardRepository,
    private val bingoCellRepository: BingoCellRepository,
    private val userRepository: UserRepository,
    private val cardFactory: CardFactory
) {
//    fun getCards(): Collection<CardDTO> {
//        val cards = bingoCardRepository.findAll()
//        val cells = bingoCellRepository.findAll()
//        return cards.map { cardFactory.createDTO(cardFactory.createBusinessModel(cells, it.cardId), it.cardId) }
//    }

    fun getCardById(cardId: UUID): CardDTO {
        val entity = bingoCardRepository.findById(cardId).orElseThrow { NotFoundException("Card") }
        val business = cardFactory.createBusinessModel(entity, entity.cells)
        return cardFactory.createDTO(business, business.id)
    }

    fun getCardIdsByUser(userId: UUID): Collection<UUID> {
        return userRepository.getReferenceById(userId).cards.map { it.cardId }
    }

    fun createCard(cardDTO: CardDTO, userId: UUID): UUID {
        val bingoCardAndCellsPair = cardFactory.createEntityModel(cardFactory.createBusinessModel(cardDTO))
        val newCard = bingoCardRepository.save(bingoCardAndCellsPair.first)
        val newCells = bingoCardAndCellsPair.second.map { bingoCellRepository.save(it) }
        val user = userRepository.findById(userId).orElseThrow()
        user.cards.add(newCard)
        userRepository.save(user)
        return newCard.cardId
    }

    fun updateCard(cardDTO: CardDTO) {
        cardFactory.createEntityModel(cardFactory.createBusinessModel(cardDTO)).second.map { bingoCellRepository.save(it) }
    }
}
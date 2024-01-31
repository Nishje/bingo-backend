package com.example.bingobackend.controller

import com.example.bingobackend.service.CardService
import com.example.bingobackend.controller.dto.CardDTO
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/cards")
class CardController(private val cardService: CardService) {
//    @GetMapping
//    fun getCards(): ResponseEntity<Map<String, Collection<CardDTO>>> {
//        return ResponseEntity.ok(mapOf(Pair("cards", cardService.getCards())))
//    }
    @GetMapping("/{id}")
    fun getSingleCardById(@PathVariable id: UUID): ResponseEntity<CardDTO> {
        return ResponseEntity.ok(cardService.getCardById(id))
    }

    @GetMapping
    fun getCardIdsOfUser(@AuthenticationPrincipal userId: UUID): ResponseEntity<Map<String, Collection<UUID>>> {
        val cardIds = cardService.getCardIdsByUser(userId)
        return ResponseEntity.ok(mapOf(Pair("cardIds", cardIds)))
    }

    @PostMapping
    fun createCard(@RequestBody cardDTO: CardDTO, @AuthenticationPrincipal userId: UUID): ResponseEntity<Map<String, UUID>> {
        val createdCardId = cardService.createCard(cardDTO, userId)
        return ResponseEntity.ok(mapOf(Pair("cardId", createdCardId)))
    }

    @PutMapping
    fun updateCard(@RequestBody cardDTO: CardDTO) {
        cardService.updateCard(cardDTO)
        return
    }
}
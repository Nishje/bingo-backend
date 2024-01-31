package com.example.bingobackend.controller

import com.example.bingobackend.service.CardService
import com.example.bingobackend.controller.dto.CardDTO
import com.example.bingobackend.util.ResponseType
import com.example.bingobackend.util.exception.NotFoundException
import org.springframework.http.HttpStatus
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
    fun getSingleCardById(@PathVariable id: UUID): ResponseEntity<ResponseType<CardDTO>> {

        return try {
            ResponseEntity.ok(ResponseType.SuccessObject(cardService.getCardById(id)))
        } catch (exception: NotFoundException) {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseType.Error(exception.message))
        }
    }

    @GetMapping
    fun getCardIdsOfUser(@AuthenticationPrincipal userId: UUID): ResponseEntity<ResponseType<Collection<UUID>>> {

        return try {
            val cardIds = cardService.getCardIdsByUser(userId)
            ResponseEntity.ok(ResponseType.SuccessMessage("cardIds", cardIds))
        } catch (exception: NotFoundException) {
            ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseType.Error(exception.message))
        }
    }

    @PostMapping
    fun createCard(
        @RequestBody cardDTO: CardDTO, @AuthenticationPrincipal userId: UUID
    ): ResponseEntity<ResponseType<UUID>> {

        val createdCardId = cardService.createCard(cardDTO, userId)
        return ResponseEntity.ok(ResponseType.SuccessMessage("cardId", createdCardId))
    }

    @PutMapping
    fun updateCard(@RequestBody cardDTO: CardDTO): ResponseEntity<ResponseType<String>> {

        cardService.updateCard(cardDTO)
        return ResponseEntity.ok(ResponseType.SuccessMessage())
    }
}
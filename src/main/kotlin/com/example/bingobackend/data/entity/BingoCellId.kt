package com.example.bingobackend.data.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.validator.constraints.Range
import java.io.Serializable

@Embeddable
data class BingoCellId(
    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.REMOVE])
//    @Transient
    @JoinColumn(name = "card_id")
    val card: BingoCard,
    val letteredColumn: LetteredColumn,
    @Range(min = 1, max = 5)
    val cellRow: Int,
) : Serializable

package com.example.bingobackend.factroy

import com.example.bingobackend.service.model.BingoCellModel
import com.example.bingobackend.controller.dto.CellDTO
import org.springframework.stereotype.Component

@Component
class CellFactory {

    fun createDTO(cellModel: BingoCellModel): CellDTO {
        return CellDTO(cellModel.content, cellModel.isChecked)
    }
    fun createBusinessModel(dto: CellDTO, index: Int): BingoCellModel {
        return BingoCellModel(dto.content, dto.isChecked, index)
    }
}
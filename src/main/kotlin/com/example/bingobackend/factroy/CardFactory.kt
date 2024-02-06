package com.example.bingobackend.factroy

import com.example.bingobackend.service.model.BingoCardModel
import com.example.bingobackend.service.model.BingoCellModel
import com.example.bingobackend.controller.dto.CardDTO
import com.example.bingobackend.controller.dto.ColumnDTO
import com.example.bingobackend.data.entity.BingoCard
import com.example.bingobackend.data.entity.BingoCell
import com.example.bingobackend.data.entity.BingoCellId
import com.example.bingobackend.data.entity.LetteredColumn
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CardFactory(
    private val cellFactory: CellFactory,
) {
    fun createDTO(business: BingoCardModel, id: UUID): CardDTO {

        return CardDTO(
            id,
            business.title,
            ColumnDTO(
                cellFactory.createDTO(business.b.elementAt(0)),
                cellFactory.createDTO(business.b.elementAt(1)),
                cellFactory.createDTO(business.b.elementAt(2)),
                cellFactory.createDTO(business.b.elementAt(3)),
                cellFactory.createDTO(business.b.elementAt(4))
            ),
            ColumnDTO(
                cellFactory.createDTO(business.i.elementAt(0)),
                cellFactory.createDTO(business.i.elementAt(1)),
                cellFactory.createDTO(business.i.elementAt(2)),
                cellFactory.createDTO(business.i.elementAt(3)),
                cellFactory.createDTO(business.i.elementAt(4))
            ),
            ColumnDTO(
                cellFactory.createDTO(business.n.elementAt(0)),
                cellFactory.createDTO(business.n.elementAt(1)),
                cellFactory.createDTO(business.n.elementAt(2)),
                cellFactory.createDTO(business.n.elementAt(3)),
                cellFactory.createDTO(business.n.elementAt(4))
            ),
            ColumnDTO(
                cellFactory.createDTO(business.g.elementAt(0)),
                cellFactory.createDTO(business.g.elementAt(1)),
                cellFactory.createDTO(business.g.elementAt(2)),
                cellFactory.createDTO(business.g.elementAt(3)),
                cellFactory.createDTO(business.g.elementAt(4))
            ),
            ColumnDTO(
                cellFactory.createDTO(business.o.elementAt(0)),
                cellFactory.createDTO(business.o.elementAt(1)),
                cellFactory.createDTO(business.o.elementAt(2)),
                cellFactory.createDTO(business.o.elementAt(3)),
                cellFactory.createDTO(business.o.elementAt(4))
            ),
        )
    }

//    fun createBusinessModel(cells: Iterable<BingoCell>, id: UUID): BingoCardModel {
//        val bCells: ArrayList<BingoCellModel> = ArrayList()
//        val iCells: ArrayList<BingoCellModel> = ArrayList()
//        val nCells: ArrayList<BingoCellModel> = ArrayList()
//        val gCells: ArrayList<BingoCellModel> = ArrayList()
//        val oCells: ArrayList<BingoCellModel> = ArrayList()
//        cells.map { bingoCell ->
//            when (bingoCell.bingoCellId.letteredColumn) {
//                LetteredColumn.B -> bCells.add(
//                    BingoCellModel(
//                        bingoCell.content,
//                        bingoCell.isChecked,
//                        bCells.lastIndex + 1
//                    )
//                )
//
//                LetteredColumn.I -> iCells.add(
//                    BingoCellModel(
//                        bingoCell.content,
//                        bingoCell.isChecked,
//                        iCells.lastIndex + 1
//                    )
//                )
//
//                LetteredColumn.N -> nCells.add(
//                    BingoCellModel(
//                        bingoCell.content,
//                        bingoCell.isChecked,
//                        nCells.lastIndex + 1
//                    )
//                )
//
//                LetteredColumn.G -> gCells.add(
//                    BingoCellModel(
//                        bingoCell.content,
//                        bingoCell.isChecked,
//                        gCells.lastIndex + 1
//                    )
//                )
//
//                LetteredColumn.O -> oCells.add(
//                    BingoCellModel(
//                        bingoCell.content,
//                        bingoCell.isChecked,
//                        oCells.lastIndex + 1
//                    )
//                )
//            }
//        }
//
//        return BingoCardModel(id, bCells, iCells, nCells, gCells, oCells)
//    }

    fun createBusinessModel(card: BingoCard, cells: Iterable<BingoCell>): BingoCardModel {
        val bCells: ArrayList<BingoCellModel> = ArrayList()
        val iCells: ArrayList<BingoCellModel> = ArrayList()
        val nCells: ArrayList<BingoCellModel> = ArrayList()
        val gCells: ArrayList<BingoCellModel> = ArrayList()
        val oCells: ArrayList<BingoCellModel> = ArrayList()
        cells.map { bingoCell ->
            when (bingoCell.bingoCellId.letteredColumn) {
                LetteredColumn.B -> bCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, bingoCell.bingoCellId.cellRow
                    )
                )

                LetteredColumn.I -> iCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, bingoCell.bingoCellId.cellRow
                    )
                )

                LetteredColumn.N -> nCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, bingoCell.bingoCellId.cellRow
                    )
                )

                LetteredColumn.G -> gCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, bingoCell.bingoCellId.cellRow
                    )
                )

                LetteredColumn.O -> oCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, bingoCell.bingoCellId.cellRow
                    )
                )
            }
        }

        return BingoCardModel(card.cardId, card.title, bCells, iCells, nCells, gCells, oCells)
    }

    fun createBusinessModel(dto: CardDTO): BingoCardModel {

        return BingoCardModel(
            dto.id ?: UUID.randomUUID(), dto.title, listOf(
                cellFactory.createBusinessModel(dto.b.cellOne, 1),
                cellFactory.createBusinessModel(dto.b.cellTwo, 2),
                cellFactory.createBusinessModel(dto.b.cellThree, 3),
                cellFactory.createBusinessModel(dto.b.cellFour, 4),
                cellFactory.createBusinessModel(dto.b.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.i.cellOne, 1),
                cellFactory.createBusinessModel(dto.i.cellTwo, 2),
                cellFactory.createBusinessModel(dto.i.cellThree, 3),
                cellFactory.createBusinessModel(dto.i.cellFour, 4),
                cellFactory.createBusinessModel(dto.i.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.n.cellOne, 1),
                cellFactory.createBusinessModel(dto.n.cellTwo, 2),
                cellFactory.createBusinessModel(dto.n.cellThree, 3),
                cellFactory.createBusinessModel(dto.n.cellFour, 4),
                cellFactory.createBusinessModel(dto.n.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.g.cellOne, 1),
                cellFactory.createBusinessModel(dto.g.cellTwo, 2),
                cellFactory.createBusinessModel(dto.g.cellThree, 3),
                cellFactory.createBusinessModel(dto.g.cellFour, 4),
                cellFactory.createBusinessModel(dto.g.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.o.cellOne, 1),
                cellFactory.createBusinessModel(dto.o.cellTwo, 2),
                cellFactory.createBusinessModel(dto.o.cellThree, 3),
                cellFactory.createBusinessModel(dto.o.cellFour, 4),
                cellFactory.createBusinessModel(dto.o.cellFive, 5)
            )
        )
    }

    fun createEntityModel(businessModel: BingoCardModel): Pair<BingoCard, Collection<BingoCell>> {
        val card = BingoCard(businessModel.id, businessModel.title, emptyList())
        val bCells: Collection<BingoCell> = extractCells(card, businessModel.b, LetteredColumn.B)
        val iCells: Collection<BingoCell> = extractCells(card, businessModel.i, LetteredColumn.I)
        val nCells: Collection<BingoCell> = extractCells(card, businessModel.n, LetteredColumn.N)
        val gCells: Collection<BingoCell> = extractCells(card, businessModel.g, LetteredColumn.G)
        val oCells: Collection<BingoCell> = extractCells(card, businessModel.o, LetteredColumn.O)

        val allCells = bCells + iCells + nCells + gCells + oCells
        return Pair(card, allCells)
    }

    private fun extractCells(
        bingoCard: BingoCard, columnModel: Iterable<BingoCellModel>, letteredColumn: LetteredColumn
    ): List<BingoCell> {
        return columnModel.map { BingoCell(BingoCellId(bingoCard, letteredColumn, it.index), it.content, it.isChecked) }
    }

}
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
                        bingoCell.content, bingoCell.isChecked, bCells.lastIndex + 1
                    )
                )

                LetteredColumn.I -> iCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, iCells.lastIndex + 1
                    )
                )

                LetteredColumn.N -> nCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, nCells.lastIndex + 1
                    )
                )

                LetteredColumn.G -> gCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, gCells.lastIndex + 1
                    )
                )

                LetteredColumn.O -> oCells.add(
                    BingoCellModel(
                        bingoCell.content, bingoCell.isChecked, oCells.lastIndex + 1
                    )
                )
            }
        }

        return BingoCardModel(card.cardId, card.title, bCells, iCells, nCells, gCells, oCells)
    }

    fun createBusinessModel(dto: CardDTO): BingoCardModel {

        return BingoCardModel(
            dto.id ?: UUID.randomUUID(), dto.title, listOf(
                cellFactory.createBusinessModel(dto.bColumn.cellOne, 1),
                cellFactory.createBusinessModel(dto.bColumn.cellTwo, 2),
                cellFactory.createBusinessModel(dto.bColumn.cellThree, 3),
                cellFactory.createBusinessModel(dto.bColumn.cellFour, 4),
                cellFactory.createBusinessModel(dto.bColumn.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.iColumn.cellOne, 1),
                cellFactory.createBusinessModel(dto.iColumn.cellTwo, 2),
                cellFactory.createBusinessModel(dto.iColumn.cellThree, 3),
                cellFactory.createBusinessModel(dto.iColumn.cellFour, 4),
                cellFactory.createBusinessModel(dto.iColumn.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.nColumn.cellOne, 1),
                cellFactory.createBusinessModel(dto.nColumn.cellTwo, 2),
                cellFactory.createBusinessModel(dto.nColumn.cellThree, 3),
                cellFactory.createBusinessModel(dto.nColumn.cellFour, 4),
                cellFactory.createBusinessModel(dto.nColumn.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.gColumn.cellOne, 1),
                cellFactory.createBusinessModel(dto.gColumn.cellTwo, 2),
                cellFactory.createBusinessModel(dto.gColumn.cellThree, 3),
                cellFactory.createBusinessModel(dto.gColumn.cellFour, 4),
                cellFactory.createBusinessModel(dto.gColumn.cellFive, 5)
            ), listOf(
                cellFactory.createBusinessModel(dto.oColumn.cellOne, 1),
                cellFactory.createBusinessModel(dto.oColumn.cellTwo, 2),
                cellFactory.createBusinessModel(dto.oColumn.cellThree, 3),
                cellFactory.createBusinessModel(dto.oColumn.cellFour, 4),
                cellFactory.createBusinessModel(dto.oColumn.cellFive, 5)
            )
        )
    }

    fun createEntityModel(businessModel: BingoCardModel): Pair<BingoCard, Collection<BingoCell>> {
        val card = BingoCard(businessModel.id, businessModel.title, emptyList());
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
        var i: Int = 0
        return columnModel.map { BingoCell(BingoCellId(bingoCard, letteredColumn, i++), it.content, it.isChecked) }
    }

}
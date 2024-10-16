package com.leanix.app.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
class Grid(
    @Id
    var id: Int = 1,

    @Lob
    @Column(columnDefinition = "TEXT")
    var gridData: String = ""
) {
    fun setGrid(grid: Array<IntArray>) {
        gridData = grid.joinToString(";") { row ->
            row.joinToString(",")
        }
    }

    fun getGrid(): Array<IntArray> {
        if (gridData.isEmpty()) return Array(50) { IntArray(50) }
        return gridData.split(";").map { row ->
            row.split(",").map { it.toInt() }.toIntArray()
        }.toTypedArray()
    }
}

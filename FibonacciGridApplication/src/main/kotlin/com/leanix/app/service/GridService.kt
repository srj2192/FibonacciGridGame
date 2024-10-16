package com.leanix.app.service

import com.leanix.app.model.Grid
import com.leanix.app.repository.GridRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class GridService(private val gridRepository: GridRepository) {

    fun getGrid(id: Int = 1): Array<IntArray> {
        val gridEntity = gridRepository.findById(id).orElse(Grid(1))
        return gridEntity.getGrid()
    }

    fun saveGrid(id: Int = 1, grid: Array<IntArray>) {
        val gridEntity = gridRepository.findById(id).orElse(Grid(1))
        gridEntity.setGrid(grid)
        gridRepository.save(gridEntity)
    }


    @Transactional
    fun clickCell(id:Int = 1, row: Int, col: Int) {
        val gridData = getGrid(id)

        for (i in 0 until 50) {
            gridData[row][i]++
            gridData[i][col]++
        }
        gridData[row][col]--

        checkSequenceAndReset(gridData)

        saveGrid(grid=gridData)
    }

    @Transactional
    fun resetGrid(id: Int = 1) {
        saveGrid(id = id, grid = Array(50) { IntArray(50) })
    }

    private fun checkSequenceAndReset(grid: Array<IntArray>) {
        // Check horizontal sequences
        for (row in 0 until 50) {
            for (col in 0 until 46) {
                val sequence = grid[row].slice(col until col + 5)
                if (isFibonacciSequence(sequence)) {
                    for (i in col until col + 5) {
                        grid[row][i] = 0
                    }
                    break
                }
            }
        }

        // Check vertical sequences
        for (col in 0 until 50) {
            for (row in 0 until 46) {
                val sequence = (row until row + 5).map { grid[it][col] }
                if (isFibonacciSequence(sequence)) {
                    for (i in row until row + 5) {
                        grid[i][col] = 0
                    }
                    break
                }
            }
        }
    }

    private fun isFibonacciSequence(sequence: List<Int>): Boolean {
        return (1 until sequence.size-1)
            .all { i -> sequence[i] + sequence[i - 1] == sequence[i + 1] }
    }
}
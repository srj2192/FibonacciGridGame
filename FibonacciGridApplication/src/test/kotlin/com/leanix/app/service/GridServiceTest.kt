package com.leanix.app.service

import com.leanix.app.model.Grid
import com.leanix.app.repository.GridRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import java.util.Optional

class GridServiceTest {

    @Mock
    private lateinit var gridRepository: GridRepository

    private lateinit var gridService: GridService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        gridService = GridService(gridRepository)
    }

    @Test
    fun `test getGrid with existing grid`() {
        val grid = Grid(1, "1,2,3;4,5,6")
        `when`(gridRepository.findById(1)).thenReturn(Optional.of(grid))

        val result = gridService.getGrid()

        assertArrayEquals(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6)), result)
    }

    @Test
    fun `test getGrid with non-existing grid`() {
        `when`(gridRepository.findById(1)).thenReturn(Optional.empty())

        val result = gridService.getGrid()

        assertEquals(50, result.size)
        assertEquals(50, result[0].size)
        assertTrue(result.all { row -> row.all { it == 0 } })
    }

    @Test
    fun `test saveGrid`() {
        val grid = Grid(1)
        `when`(gridRepository.findById(1)).thenReturn(Optional.of(grid))

        val newGrid = Array(50) { IntArray(50) { 1 } }
        gridService.saveGrid(grid = newGrid)

        verify(gridRepository).save(grid)
        assertEquals(newGrid.joinToString(";") { it.joinToString(",") }, grid.gridData)
    }

    @Test
    fun `test clickCell`() {
        val initialGrid = Array(50) { IntArray(50) }
        val grid = Grid(1)
        grid.setGrid(initialGrid)
        `when`(gridRepository.findById(1)).thenReturn(Optional.of(grid))

        gridService.clickCell(row = 1, col = 1)

        val updatedGrid = grid.getGrid()
        assertEquals(50, updatedGrid[1].sum())
        assertEquals(50, updatedGrid.sumOf { it[1] })
        assertEquals(1, updatedGrid[1][1])

        verify(gridRepository).save(grid)
    }

    @Test
    fun `test resetGrid`() {
        val grid = Grid(1)
        `when`(gridRepository.findById(1)).thenReturn(Optional.of(grid))

        gridService.resetGrid()

        val resetGrid = grid.getGrid()
        assertEquals(50, resetGrid.size)
        assertEquals(50, resetGrid[0].size)
        assertTrue(resetGrid.all { row -> row.all { it == 0 } })

        verify(gridRepository).save(grid)
    }

    @Test
    fun `test checkFibonacciSequence horizontal`() {
        val grid = Array(50) { IntArray(50) }

        grid[0] = IntArray(50) {0}
        intArrayOf(0, 0, 1, 2, 4, 7, 12, 20, 33, 54).copyInto(grid[0])
        val gridEntity = Grid(1)
        gridEntity.setGrid(grid)
        `when`(gridRepository.findById(1)).thenReturn(Optional.of(gridEntity))

        gridService.clickCell(row = 0, col = 0)

        val updatedGrid = gridEntity.getGrid()

        val expected = intArrayOf(0, 0, 0, 0, 0)
        val actual = updatedGrid[0].take(5).toIntArray()

        assertArrayEquals(expected, actual)
    }

    @Test
    fun `test checkFibonacciSequence vertical`() {
        val grid = Array(50) { IntArray(50) }
        for (i in 0..9) {
            grid[i][0] = when (i) {
                0 -> 0
                1 -> 0
                2 -> 1
                3 -> 2
                4 -> 4
                5 -> 7
                6 -> 12
                7 -> 20
                8 -> 33
                9 -> 54
                else -> 0
            }
        }
        val gridEntity = Grid(1)
        gridEntity.setGrid(grid)
        `when`(gridRepository.findById(1)).thenReturn(Optional.of(gridEntity))

        gridService.clickCell(row = 0, col = 0)

        val updatedGrid = gridEntity.getGrid()
        for (i in 0..4) {
            assertEquals(0, updatedGrid[i][0])
        }
    }

    @Test
    fun `test checkFibonacciSequence vertical center`() {
        val grid = Array(50) { IntArray(50) }
        for (i in 0..9) {
            grid[i][0] = when (i) {
                2 -> 0
                3 -> 0
                4 -> 1
                5 -> 2
                6 -> 4
                else -> 0
            }
        }
        val gridEntity = Grid(1)
        gridEntity.setGrid(grid)
        `when`(gridRepository.findById(1)).thenReturn(Optional.of(gridEntity))

        gridService.clickCell(row = 2, col = 0)

        val updatedGrid = gridEntity.getGrid()
        for (i in 2..6) {
            assertEquals(0, updatedGrid[i][0])
        }
    }
}

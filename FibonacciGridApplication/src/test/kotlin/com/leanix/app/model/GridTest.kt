package com.leanix.app.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GridTest {

    @Test
    fun `test Grid constructor`() {
        val grid = Grid(1, "1,2,3;4,5,6")
        assertEquals(1, grid.id)
        assertEquals("1,2,3;4,5,6", grid.gridData)
    }

    @Test
    fun `test setGrid`() {
        val grid = Grid()
        val testGrid = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6)
        )
        grid.setGrid(testGrid)
        assertEquals("1,2,3;4,5,6", grid.gridData)
    }

    @Test
    fun `test getGrid with non-empty gridData`() {
        val grid = Grid(1, "1,2,3;4,5,6")
        val result = grid.getGrid()
        assertArrayEquals(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6)), result)
    }

    @Test
    fun `test getGrid with empty gridData`() {
        val grid = Grid()
        val result = grid.getGrid()
        assertEquals(50, result.size)
        assertEquals(50, result[0].size)
        assertTrue(result.all { row -> row.all { it == 0 } })
    }

    @Test
    fun `test setGrid and getGrid integration`() {
        val grid = Grid()
        val testGrid = Array(50) { row ->
            IntArray(50) { col -> row * 50 + col }
        }
        grid.setGrid(testGrid)
        val result = grid.getGrid()
        assertArrayEquals(testGrid, result)
    }
}
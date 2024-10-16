package com.leanix.app.controller

import com.leanix.app.service.GridService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RequestMapping("/grid")
class GridController(private val gridService: GridService) {


    @GetMapping
    fun getGrid(): Array<IntArray> {
        return gridService.getGrid()
    }

    @PostMapping("/{gridId}/click/{row}/{col}")
    fun updateGrid(
        @PathVariable gridId: Int,
        @PathVariable row: Int,
        @PathVariable col: Int
    ) {
        return gridService.clickCell(gridId, row, col)
    }

    @PutMapping("/reset/{gridId}")
    fun updateCell(
        @PathVariable gridId: Int
    ) {
        return gridService.resetGrid(gridId)
    }
}

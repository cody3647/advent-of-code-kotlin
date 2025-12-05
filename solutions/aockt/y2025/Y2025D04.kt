package aockt.y2025

import io.github.jadarma.aockt.core.Solution

object Y2025D04: Solution {
    fun String.parseInput() = lines().map { it.toCharArray() }

    fun List<CharArray>.calculatePartOne(): Int {
        val rows = size
        val columns = first().size
        var count = 0
        for (rowNum in 0 until rows) {
            val row = this[rowNum]
            for (colNum in 0 until columns) {
                if (row[colNum] == '@') {
                    var adjacent = -1
                    val startRow = if (rowNum > 0) rowNum - 1 else 0
                    val endRow = if (rowNum < rows - 1) rowNum + 1 else rowNum
                    val startCol = if (colNum > 0) colNum - 1 else 0
                    val endCol = if (colNum < columns - 1) colNum + 1 else colNum
                    for (i in startRow..endRow) {
                        for (j in startCol..endCol) {
                            if (this[i][j] == '@') adjacent++
                        }
                    }
                    if (adjacent < 4) count++
                }
            }
        }

        return count
    }

    fun List<CharArray>.calculatePartTwo(): Int {
        val rows = size
        val columns = first().size
        var count = 0
        do {
            var changed = false
            for (rowNum in 0 until rows) {
                val row = this[rowNum]
                for (colNum in 0 until columns) {
                    if (row[colNum] == '@') {
                        var adjacent = -1
                        val startRow = if (rowNum > 0) rowNum - 1 else 0
                        val endRow = if (rowNum < rows - 1) rowNum + 1 else rowNum
                        val startCol = if (colNum > 0) colNum - 1 else 0
                        val endCol = if (colNum < columns - 1) colNum + 1 else colNum
                        for (i in startRow..endRow) {
                            for (j in startCol..endCol) {
                                if (this[i][j] == '@') adjacent++
                            }
                        }
                        if (adjacent < 4) {
                            count++
                            row[colNum] = '.'
                            changed = true
                        }
                    }
                }
            }
        } while (changed)


        return count
    }

    override fun partOne(input: String): Any {
        return input.parseInput().calculatePartOne().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInput().calculatePartTwo().also { println(it) }
    }
}
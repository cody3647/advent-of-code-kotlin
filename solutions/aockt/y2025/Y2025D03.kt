package aockt.y2025

import io.github.jadarma.aockt.core.Solution

object Y2025D03: Solution {
    fun String.parseInput() = lines()

    fun List<String>.calculatePartOne(): Long {
        return this.sumOf { bank ->
            var maxTen = '0'
            var tenPosition = 0
            var maxOne = '0'
            for (i in 0 until bank.length - 1) {
                if (bank[i] > maxTen) {
                    maxTen = bank[i]
                    tenPosition = i
                }
            }
            for (i in tenPosition + 1 until bank.length) {
                if (bank[i] > maxOne) {
                    maxOne = bank[i]
                }
            }

            "$maxTen$maxOne".toLong().also { println(it) }
        }
    }

    fun List<String>.calculatePartTwo(): Long {
        return this.sumOf { bank ->
            buildString {
                var lastPosition = -1
                for (digit in 0..11) {
                    var max = '0'
                    val start = lastPosition + 1
                    val end = bank.length - (12 - digit)
                    for (i in start..end) {
                        if (bank[i] > max) {
                            max = bank[i]
                            lastPosition = i
                        }
                    }
                    append(max)
                }
            }.toLong().also { println(it) }
        }
    }

    override fun partOne(input: String): Any {
        return input.parseInput().calculatePartOne().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInput().calculatePartTwo().also { println(it) }
    }
}
package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import kotlin.comparisons.compareBy

object Y2025D05 : Solution {

    fun String.parseInput(): Pair<List<LongRange>, List<Long>> {
        return try {
            val lines = lines()
            val index = lines.indexOf("")
            val fresh = lines.subList(0, index)
            val ingredient = lines.subList(index + 1, lines.size)

            fresh.map {
                val (a, b) = it.split("-")
                a.toLong()..b.toLong()
            } to ingredient.map { it.toLong() }
        } catch (e: Exception) {
            println(e)
            Pair(emptyList(), emptyList())
        }
    }

    fun Pair<List<LongRange>, List<Long>>.calculatePartOne(): Int {
        val (freshRanges, ingredients) = this
        return ingredients.count { ingredient ->
            freshRanges.any { range ->
                ingredient in range
            }
        }
    }

    fun Pair<List<LongRange>, List<Long>>.calculatePartTwo(): Long {
        return try {
            val freshRanges = this.first
                .sortedWith (compareBy( { it.first }, { it.last }))
                .toMutableList()
                .fold(emptyList<LongRange>()) { acc, next ->
                    val prev = acc.lastOrNull() ?: return@fold listOf(next)
                    if (next.first > prev.last + 1) {
                        acc.plusElement(next)
                    } else if (next.last > prev.last) {
                        acc.dropLast(1).plusElement(prev.first..next.last)
                    } else {
                        acc
                    }
                }

            freshRanges.sumOf { range -> range.last - range.first + 1 }
        } catch (e: Exception) {
            println(e)
            e.printStackTrace()
            0
        }
    }

    override fun partOne(input: String): Any {
        return input.parseInput().calculatePartOne().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInput().calculatePartTwo().also { println(it) }
    }
}
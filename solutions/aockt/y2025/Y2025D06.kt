package aockt.y2025

import io.github.jadarma.aockt.core.Solution

object Y2025D06 : Solution {

    fun String.parseInputOne(): List<Pair<List<Long>, String>> {
        val splitLines = lines()
            .map { it.trim().split(" +".toRegex()) }
        return buildList {
            for (i in 0 until splitLines.first().size) {
                add(
                    buildList {
                        for (k in 0 until splitLines.size - 1) {
                            add(splitLines[k][i].toLong())
                        }
                    } to splitLines.last()[i]
                )
            }
        }
    }

    fun String.parseInputTwo(): List<Pair<List<Long>, String>> {
        val lines = lines()
        val widths = lines.last().split("+", "*").map { it.length }.drop(1).dropLast(1)
        val splitLines = lines.map { line ->
            buildList {
                var start = 0
                widths.forEach { width ->
                    add(line.substring(start, start + width))
                    start += width + 1
                }
                add(line.substring(start))
            }
        }

        val columns = buildList {
            for (i in 0 until splitLines.first().size) {
                add(
                    buildList {
                        for (k in 0 until splitLines.size - 1) {
                            add(splitLines[k][i])
                        }
                    } to splitLines.last()[i].trim()
                )
            }
        }

        return columns.map { (list, op) ->
            val width = list.first().length
            buildList {
                for (i in 0 until width) {
                    add(
                        buildString {
                            list.forEach { append(it[i]) }
                        }.trim().toLong()
                    )
                }
            } to op
        }
    }

    fun List<Pair<List<Long>, String>>.calculate() = try {
        sumOf {
            when (it.second) {
                "+" -> it.first.sumOf { it }
                "*" -> it.first.fold(1L) { acc, next -> acc * next }
                else -> error("Unknown operation $it")
            }
        }
    } catch (e: Exception) {
        println(e)
        0
    }

    override fun partOne(input: String): Any {
        return input.parseInputOne().calculate().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInputTwo().calculate().also { println(it) }
    }
}
package aockt.y2025

import io.github.jadarma.aockt.core.Solution

object Y2025D02 : Solution {
    fun String.parseInput() = split(",")
        .map {
            val (s1, s2) = it.split('-')
            s1.toLong()..s2.toLong()
        }

    fun List<LongRange>.calculatePartOne() = sequence {
            forEach { range ->
                for (i in range) {
                    if (i < 10) continue

                    val str = i.toString()
                    val half = str.length / 2
                    val str1 = str.take(half)
                    val str2 = str.substring(half)
                    if (str1 == str2) {
                        yield(i)
                    }
                }
            }
        }.sum()

    fun List<LongRange>.calculatePartTwo() = sequence {
            forEach { range ->
                for (i in range) {
                    if (i < 10) continue
                    val str = i.toString()
                    val half = str.length / 2

                    for (j in half downTo 1) {
                        val distinctChunks = str.chunked(j).distinct()
                        if (distinctChunks.size == 1) {
                            yield(i)
                            break
                        }
                    }
                }
            }
        }.sum()

    override fun partOne(input: String): Any {
        return input.parseInput().calculatePartOne().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInput().calculatePartTwo().also { println(it) }
    }
}
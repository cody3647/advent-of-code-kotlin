package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import kotlin.text.HexFormat

object Y2025D07: Solution {
    fun String.parseInput() = lines().map { it.toMutableList() }


    fun List<MutableList<Char>>.calculatePartOne(): Int {
        var count = 0

        println(first().joinToString(""))
        for (i in 0 until size - 1) {
            val line = get(i)
            val next = get(i + 1)

            line.forEachIndexed { index, c ->
                if (c == 'S' || c == '|') {
                    when (next[index]) {
                        '.' -> next[index] = '|'
                        '^' -> {
                            next[index-1] = '|'
                            next[index+1] = '|'
                            count++
                        }
                    }
                }
            }
            println(next.joinToString(""))
        }
        return count
    }

    fun List<MutableList<Char>>.calculatePartTwo(): Long {
        val format = HexFormat {
            number.removeLeadingZeros = true
            number.prefix = ""
            number.suffix = ""
        }
        val counts = mutableListOf<MutableList<Long>>()
        counts.add(first().map { if (it == 'S') 1L else 0L }.toMutableList())

        for (i in 0 until size - 1) {
            val line = get(i)
            val next = get(i + 1)
            val prevCount = counts.last()
            val count = line.map { 0L }.toMutableList().also { counts.add(it)}

            print("$i\t")
            line.forEachIndexed { index, c ->
                print("$c")
                if (c == 'S' || c == '|') {
                    when (next[index]) {
                        '.', '|' -> {
                            next[index] = '|'
                            count[index] = count[index] + prevCount[index]
                        }
                        '^' -> {
                            val prev = prevCount[index]
                            next[index-1] = '|'
                            next[index+1] = '|'
                            count[index-1] = count[index-1] + prev
                            count[index+1] = count[index+1] + prev
                        }
                    }
                }
            }

            print("\t")
            if (i == 0) {
                print(get(i).joinToString(""))
            } else {
                counts[i].mapIndexed { index, count ->
                    if (count == 0L) get(i)[index] else count.toHexString(format)
                }.joinToString("").also { print(it) }
            }
            println()
        }


        return counts.last().sum()
    }

    override fun partOne(input: String): Any {
        return input.parseInput().calculatePartOne().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInput().calculatePartTwo().also { println(it) }
    }
}
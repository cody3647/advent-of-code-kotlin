package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import kotlin.math.absoluteValue
import kotlin.math.sign

object Y2025D01: Solution {

    fun String.parseInput() = lines().map {
        val direction = it.first()
        val amount = it.substring(1).toInt()
        if (direction == 'L') {
            -amount
        } else {
            amount
        }
    }

    fun List<Int>.calculatePasswordPartOne(): Int {
        var position = 50
        return count {
            position += it
            position = position.mod(100)
            position == 0
        }
    }

    fun List<Int>.calculatePasswordPartTwo(): Int {
        var position = 50
        var count = 0
         forEach {
             val rot = it.sign
             repeat(it.absoluteValue) {
                 if (position == 0 && rot == -1) position = 99
                 else if (position == 99 && rot == 1) position = 0
                 else position += rot

                 if (position == 0) {
                     count++
                 }
             }
         }

        return count
    }


    override fun partOne(input: String): Any {
        return input.parseInput().calculatePasswordPartOne().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInput().calculatePasswordPartTwo().also { println(it) }
    }
}
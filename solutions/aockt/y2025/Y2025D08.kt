package aockt.y2025

import io.github.jadarma.aockt.core.Solution
import java.math.BigInteger

object Y2025D08 : Solution {
    data class JunctionBox(
        val index: Int,
        val x: BigInteger, val y: BigInteger, val z: BigInteger
    )

    fun String.toJunctionBox(index: Int): JunctionBox {
        val (x, y, z) = split(",").map { BigInteger(it) }
        return JunctionBox(index, x, y, z)
    }

    data class Connection(
        val from: JunctionBox, val to: JunctionBox
    ) {
        val distanceSquared = from.x.subtract(to.x).pow(2) + from.y.subtract(to.y).pow(2) + from.z.subtract(to.z).pow(2)
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Connection) return false
            if (from == other.from && to == other.to) return true
            if (from == other.to && to == other.from) return true
            return false
        }

        override fun hashCode(): Int {
            var result = from.hashCode()
            result += to.hashCode()
            return result * 31
        }

        override fun toString() = "${from.index} -> ${to.index}"
    }

    data class Circuit(val name: Int, val junctionBoxes: MutableSet<JunctionBox> = mutableSetOf()) {
        fun add(connection: Connection) {
            junctionBoxes.add(connection.from)
            junctionBoxes.add(connection.to)
        }

        fun merge(circuit: Circuit) {
            junctionBoxes.addAll(circuit.junctionBoxes)
        }

        fun connected(connection: Connection) =
            junctionBoxes.contains(connection.from) || junctionBoxes.contains(connection.to)

        override fun toString() =
            junctionBoxes.joinToString(prefix = "Circuit $name: ", separator = ", ") { it.index.toString() }
    }

    fun String.parseInput(): Pair<Int, List<Connection>> {
        val junctionBoxes = lines().mapIndexed { index, string -> string.toJunctionBox(index) }

        return Pair(junctionBoxes.size, buildSet<Connection> {
            for (i in 0 until junctionBoxes.size) {
                for (j in i + 1 until junctionBoxes.size) {
                    add(Connection(junctionBoxes[i], junctionBoxes[j]))
                }
            }
        }
            .toSortedSet { a, b -> a.distanceSquared.compareTo(b.distanceSquared) }
            .toList()
        )
    }

    fun Pair<Int, List<Connection>>.calculatePartOne(): Long {
        val (size, list) = this
        val circuits = mutableListOf<Circuit>()
        var name = 1
        val number = if (size < 50) {
            10
        } else {
            1000
        }
        list.take(number).forEach { connection ->
            val possibleConnections = circuits.filter { it.connected(connection) }.ifEmpty {
                Circuit(name++).also { circuits.add(it) }.let { listOf(it) }
            }

            if (possibleConnections.size == 1) {
                possibleConnections.first().add(connection)
            } else if (possibleConnections.size == 2) {
                possibleConnections.reduce { acc, circuit -> acc.also { it.merge(circuit) } }
                circuits.remove(possibleConnections.last())
            }

        }
        circuits.forEach { println(it) }
        return circuits.sortedByDescending { it.junctionBoxes.size }.take(3).map { it.junctionBoxes.size.toLong() }
            .fold(1L) { acc, next -> acc * next }
    }

    fun Pair<Int, List<Connection>>.calculatePartTwo(): BigInteger {
        val (size, list) = this
        val connectedBoxes = mutableSetOf<JunctionBox>()
        val circuits = mutableListOf<Circuit>()
        var name = 1

        list.forEach { connection ->
            val possibleConnections = circuits.filter { it.connected(connection) }.ifEmpty {
                Circuit(name++).also { circuits.add(it) }.let { listOf(it) }
            }

            if (possibleConnections.size == 1) {
                possibleConnections.first().add(connection)
            } else if (possibleConnections.size == 2) {
                possibleConnections.reduce { acc, circuit -> acc.also { it.merge(circuit) } }
                circuits.remove(possibleConnections.last())
            }
            connectedBoxes.add(connection.from)
            connectedBoxes.add(connection.to)

            if (connectedBoxes.size == size && circuits.size == 1) {
                return connection.to.x * connection.from.x
            }
        }
        return BigInteger.ZERO
    }


    override fun partOne(input: String): Any {

        return input.parseInput().calculatePartOne().also { println(it) }
    }

    override fun partTwo(input: String): Any {
        return input.parseInput().calculatePartTwo().also { println(it) }
    }
}
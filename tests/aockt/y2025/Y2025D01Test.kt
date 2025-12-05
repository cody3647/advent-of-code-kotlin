package aockt.y2025

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2025, 1, "Secret Entrance")
class Y2025D01Test : AdventSpec<Y2025D01>({
    partOne() {
        """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent() shouldOutput 3

        """
            L0
            R0
        """.trimIndent() shouldOutput 0
    }

    partTwo() {
        """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent() shouldOutput 6

        listOf(
            """
                L50
                L200
            """.trimIndent(),
            """
                R50
                L200
            """.trimIndent(),
            """
                L50
                R200
            """.trimIndent(),
            """
                R50
                R200
            """.trimIndent()
        ) shouldAllOutput 3

        listOf(
            """
                L50
                L100
            """.trimIndent(),
            """
                R50
                L100
            """.trimIndent(),
            """
                L50
                R100
            """.trimIndent(),
            """
                R50
                R100
            """.trimIndent()
        ) shouldAllOutput 2

        """
            L0
            R0
        """.trimIndent() shouldOutput 0

        listOf(
            """
                R600
            """.trimIndent(),
            """
                L600
            """.trimIndent(),
            """
                R100
                R100
                R100
                R100
                R100
                R100
            """.trimIndent()
        ) shouldAllOutput 6

        listOf(
            """
                R1000
            """.trimIndent(),
            """
                L1000
            """.trimIndent(),
        ) shouldAllOutput 10

        listOf(
            """
                R650
            """.trimIndent(),
            """
                L650
            """.trimIndent(),
            """
                R100
                R100
                R100
                R100
                R100
                R100
                R50
            """.trimIndent()
        ) shouldAllOutput 7

        """
            L600
            R600
        """.trimIndent() shouldOutput 12
    }
})
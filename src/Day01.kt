fun main() {

    val words: Map<String, Int> = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
    )

    fun calibrationValue(row: String) : Int =
        "${row.find { it.isDigit() }}${row.last { it.isDigit() }}".toInt()

    fun part1(input: List<String>): Int =
        input.sumOf { calibrationValue(it) }

    fun part2(input: List<String>): Int =
        input.sumOf { row ->
            calibrationValue(
                    row.mapIndexedNotNull { index, c ->
                        if (c.isDigit()) c
                        else
                            row.possibleWordsAt(index).firstNotNullOfOrNull { candidate ->
                                words[candidate]
                            }
                    }.joinToString()
            )
        }

    val testInput = readInput("input/Day01_test1")
    check(part1(testInput) == 142)

    val input = readInput("input/Day01_task1")
    part1(input).println()
    part2(input).println()
}

private fun String.possibleWordsAt(staringAt: Int) : List<String> =
        (3..5).map { len ->
            substring(staringAt, (staringAt + len).coerceAtMost(length))
        }
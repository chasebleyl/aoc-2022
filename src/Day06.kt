fun String.allCharsUnique(): Boolean {
    val uniques = mutableSetOf<Char>()
    this.forEach { char ->
        uniques.add(char)
    }
    return uniques.size == this.length
}

fun main() {
    fun part1(input: List<String>): Int {
        val datastream = input[0]
        if (datastream.length < 4) return 0
        for (i in 4 until datastream.length+1) {
            val currentSubstring = datastream.substring(i-4, i)
            if (currentSubstring.allCharsUnique()) return i
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val datastream = input[0]
        if (datastream.length < 14) return 0
        println("datastream=$datastream")
        for (i in 14 until datastream.length+1) {
            val currentSubstring = datastream.substring(i-14, i)
            if (currentSubstring.allCharsUnique()) return i
        }
        return 0
    }

    val input = readInput("Day06")
    val testInput = readInput("Day06_test")
    // PART 1
    check(part1(testInput) == 7)
    println(part1(input))

    // PART 2
    check(part2(testInput) == 19)
    println(part2(input))
}

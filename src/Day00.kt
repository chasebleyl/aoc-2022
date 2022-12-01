
fun main() {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 2
    }

    val input = readInput("Day00")
    val testInput = readInput("Day00_test")
    // PART 1
    check(part1(testInput) == 1)
    println(part1(input))

    // PART 2
    check(part2(testInput) == 2)
    println(part2(input))
}

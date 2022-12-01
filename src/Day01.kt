import java.math.BigInteger

fun main() {
    fun sortedElvesByCalories(input: List<String>): List<BigInteger> {
        val elfCalories = mutableListOf<BigInteger>()
        var currentElfCalories = BigInteger.ZERO
        input.forEach { calories ->
            if (calories.isEmpty()) {
                elfCalories.add(currentElfCalories)
                currentElfCalories = BigInteger.ZERO
            } else {
                currentElfCalories += BigInteger(calories)
            }
        }
        elfCalories.add(currentElfCalories)
        return elfCalories.sortedDescending()
    }

    fun part1(input: List<String>): BigInteger {
        return sortedElvesByCalories(input).first()
    }

    fun part2(input: List<String>): BigInteger {
        val sortedElvesCaloriesCounts = sortedElvesByCalories(input)
        var caloriesSum = BigInteger.ZERO
        for (i in 0..2) {
            caloriesSum += sortedElvesCaloriesCounts[i]
        }
        return caloriesSum
    }

    val input = readInput("Day01")
    val testInput = readInput("Day01_test")
    // PART 1
    check(part1(testInput) == BigInteger.valueOf(24000))
    println(part1(input))

    // PART 2
    check(part2(testInput) == BigInteger.valueOf(45000))
    println(part2(input))
}

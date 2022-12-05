
fun Char.toPriorityValue(): Int {
    // LOWERCASE a-z 97-122
    if (this.code in 97..122) return this.code - 96
    // UPPERCASE A-Z 65-90, with a 26 priority drop due to uppercase (lowercase prioritized ahead of uppercase)
    return this.code - 64 + 26
}

fun main() {
    fun part1(input: List<String>): Int {
        var prioritySum = 0
        input.forEach { rucksack ->
            val compartmentOne = rucksack.substring(0, (rucksack.length / 2))
            val compartmentTwo = rucksack.substring((rucksack.length / 2))
            compartment@ for (item in compartmentOne) {
                if (compartmentTwo.contains(item)) {
                    prioritySum += item.toPriorityValue()
                    break@compartment
                }
             }
        }
        return prioritySum
    }

    fun part2(input: List<String>): Int {
        var prioritySum = 0
        for (groupCounter in 0..(input.size / 3 - 1)) {
            val itemRedundancies = mutableSetOf<Char>()
            val currentStartingIndex = groupCounter * 3
            val elfOne = input[currentStartingIndex]
            val elfTwo = input[currentStartingIndex+1]
            val elfThree = input[currentStartingIndex+2]
            for (item in elfOne) {
                if (elfTwo.contains(item)) {
                    itemRedundancies.add(item)
                }
            }
            elf@ for (item in itemRedundancies) {
                if (elfThree.contains(item)) {
                    prioritySum += item.toPriorityValue()
                    break@elf
                }
            }
        }
        return prioritySum
    }

    val input = readInput("Day03")
    val testInput = readInput("Day03_test")
    // PART 1
    check(part1(testInput) == 157)
    println(part1(input))

    // PART 2
    check(part2(testInput) == 70)
    println(part2(input))
}


data class Stack(
    val id: Int,
    val crates: ArrayDeque<String> = ArrayDeque(),
)
fun ArrayDeque<String>.removeLastN(n: Int): ArrayDeque<String> {
    val elements = ArrayDeque<String>()
    for (i in 1..n) {
        elements.addFirst(this.removeLast())
    }
    return elements
}
fun ArrayDeque<String>.addLastN(elements: ArrayDeque<String>) {
    for (i in 1..elements.size) {
        this.addLast(elements.removeFirst())
    }
}
data class StackAction(
    val number: Int,
    val from: Int,
    val to: Int,
) {
    companion object {
        operator fun invoke(matchResult: MatchResult) = StackAction(
            matchResult.groupValues[1].toInt(),
            matchResult.groupValues[2].toInt(),
            matchResult.groupValues[3].toInt(),
        ) 
    }
}

fun buildEmptyStacks(input: String): List<Stack> =
    mutableListOf<Stack>().let { stackList ->
        input.chunked(4).forEachIndexed { index, _ ->
            stackList.add(Stack(index + 1))
        }
        stackList
    }

fun buildStacks(input: List<String>): List<Stack> {
    val stackAccumulators = buildEmptyStacks(input[0])
    drawing@ for(line in input) {
        if (line.isEmpty()) break@drawing
        val rawCrates = line.chunked(4)
        for (i in rawCrates.indices) {
            val supply = rawCrates[i].replace("[","").replace("]","").replace(" ","")
            if (supply.isNotEmpty() && !supply.all { char -> char.isDigit() }) {
                stackAccumulators[i].crates.addFirst(supply)    
            }
        }
    }
    return stackAccumulators
}
fun main() {
    fun part1(input: List<String>): String {
        val stacks = buildStacks(input)
        input.forEach { line ->
            if (line.contains("move")) {
                """move\s(\d+)\sfrom\s(\d+)\sto\s(\d+)""".toRegex().find(line)?.let { matchResult ->
                    val stackAction = StackAction(matchResult)
                    stacks.find { it.id == stackAction.to }?.let { destination ->
                        stacks.find { it.id == stackAction.from }?.let { origin ->
                            for (i in 1..stackAction.number) {
                                destination.crates.addLast(origin.crates.removeLast())
                            }
                        }    
                    }
                }
            }
        }
        var topSupplies = ""
        stacks.forEach { stack ->
            topSupplies += stack.crates.last()
        }
        return topSupplies
    }

    fun part2(input: List<String>): String {
        val stacks = buildStacks(input)
        input.forEach { line ->
            if (line.contains("move")) {
                """move\s(\d+)\sfrom\s(\d+)\sto\s(\d+)""".toRegex().find(line)?.let { matchResult ->
                    val stackAction = StackAction(matchResult)
                    stacks.find { it.id == stackAction.to }?.let { destination ->
                        stacks.find { it.id == stackAction.from }?.let { origin ->
                            destination.crates.addLastN(origin.crates.removeLastN(stackAction.number))
                        }
                    }
                }
            }
        }
        var topSupplies = ""
        stacks.forEach { stack ->
            topSupplies += stack.crates.last()
        }
        return topSupplies
    }

    val input = readInput("Day05")
    val testInput = readInput("Day05_test")
    // PART 1
    check(part1(testInput) == "CMZ")
    println(part1(input))

    // PART 2
    check(part2(testInput) == "MCD")
    println(part2(input))
}

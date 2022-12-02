
enum class RPS {
    ROCK, PAPER, SCISSORS, UNKNOWN
}
fun RPS.scoreValue(): Int = when (this) {
    RPS.ROCK -> 1
    RPS.PAPER -> 2
    RPS.SCISSORS -> 3
    RPS.UNKNOWN -> 0
}
fun String.toRPS(): RPS = when (this) {
    "A", "X" -> RPS.ROCK
    "B", "Y" -> RPS.PAPER
    "C", "Z" -> RPS.SCISSORS
    else -> RPS.UNKNOWN
}
enum class Outcome {
    WIN, LOSE, DRAW
}
fun Outcome.scoreValue(): Int = when (this) {
    Outcome.WIN -> 6
    Outcome.LOSE -> 0
    Outcome.DRAW -> 3
}
fun String.toOutcome(): Outcome = when (this) {
    "X" -> Outcome.LOSE
    "Y" -> Outcome.DRAW
    else -> Outcome.WIN
}
fun rpsOutcome(opponent: RPS, self: RPS): Outcome = when {
    opponent == RPS.ROCK && self == RPS.SCISSORS
    || opponent == RPS.PAPER && self == RPS.ROCK
    || opponent == RPS.SCISSORS && self == RPS.PAPER -> Outcome.LOSE
    opponent == self -> Outcome.DRAW
    else -> Outcome.WIN
}
fun calculateSelfShape(opponent: RPS, outcome: Outcome): RPS = when {
    outcome == Outcome.WIN -> when (opponent) {
        RPS.ROCK -> RPS.PAPER
        RPS.PAPER -> RPS.SCISSORS
        RPS.SCISSORS -> RPS.ROCK
        RPS.UNKNOWN -> RPS.UNKNOWN
    }
    outcome == Outcome.LOSE -> when (opponent) {
        RPS.ROCK -> RPS.SCISSORS
        RPS.PAPER -> RPS.ROCK
        RPS.SCISSORS -> RPS.PAPER
        RPS.UNKNOWN -> RPS.UNKNOWN
    }
    else -> opponent
}

fun main() {
    fun part1(input: List<String>): Int {
        var runningScore = 0
        input.forEach { game ->
            val throws = game.split(" ")
            runningScore +=
                throws[1].toRPS().scoreValue() + rpsOutcome(throws[0].toRPS(), throws[1].toRPS()).scoreValue()
        }
        return runningScore
    }

    fun part2(input: List<String>): Int {
        var runningScore = 0
        input.forEach { game ->
            val throws = game.split(" ")
            val self = calculateSelfShape(throws[0].toRPS(), throws[1].toOutcome())
            runningScore +=
                self.scoreValue() + rpsOutcome(throws[0].toRPS(), self).scoreValue()
        }
        return runningScore
    }

    val input = readInput("Day02")
    val testInput = readInput("Day02_test")
    // PART 1
    check(part1(testInput) == 15)
    println(part1(input))

    // PART 2
    check(part2(testInput) == 12)
    println(part2(input))
}

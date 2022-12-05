
// Formatted "N-N", where input is string and N represents an Integer
fun String.toAssignment() = this.split("-").let { splitSections ->
    Assignment(splitSections[0].toInt(), splitSections[1].toInt())
}

data class Assignment(
    val lowerSection: Int,
    val upperSection: Int,
) {
    fun encapsulates(assignment: Assignment) = this.lowerSection <= assignment.lowerSection
        && this.upperSection >= assignment.upperSection
    fun overlaps(assignment: Assignment) =
        if (this.lowerSection >= assignment.lowerSection && this.lowerSection <= assignment.upperSection) true
        else if (this.upperSection <= assignment.upperSection && this.upperSection >= assignment.lowerSection) true
        else if (assignment.lowerSection >= this.lowerSection && assignment.lowerSection <= this.upperSection) true
        else assignment.upperSection <= this.upperSection && assignment.upperSection >= this.lowerSection
}

fun main() {
    fun part1(input: List<String>): Int {
        var encapsulatedCounter = 0
        input.forEach { assignments ->
            val splitAssignments = assignments.split(",")
            val assignmentOne = splitAssignments[0].toAssignment()
            val assignmentTwo = splitAssignments[1].toAssignment()
            if (assignmentOne.encapsulates(assignmentTwo) || assignmentTwo.encapsulates(assignmentOne)) {
                encapsulatedCounter += 1
            }
        }
        return encapsulatedCounter
    }

    fun part2(input: List<String>): Int {
        var overlappedCounter = 0
        input.forEach { assignments ->
            val splitAssignments = assignments.split(",")
            val assignmentOne = splitAssignments[0].toAssignment()
            val assignmentTwo = splitAssignments[1].toAssignment()
            if (assignmentOne.overlaps(assignmentTwo)) {
                overlappedCounter += 1
            }
        }
        return overlappedCounter
    }

    val input = readInput("Day04")
    val testInput = readInput("Day04_test")
    // PART 1
    check(part1(testInput) == 2)
    println(part1(input))

    // PART 2
    check(part2(testInput) == 4)
    println(part2(input))
}

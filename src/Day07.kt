
data class Directory(
    val name: String,
    val parent: Directory? = null,
    val subDirectories: MutableList<Directory> = mutableListOf<Directory>(),
    val files: MutableList<File> = mutableListOf<File>(),
) {
    fun allSubDirectories(): List<Directory> = subDirectories + subDirectories.flatMap { it.allSubDirectories() }
    fun size(): Int = files.sumOf { it.size } + subDirectories.sumOf { it.size() }
}
data class File(
    val size: Int,
    val name: String,
)

fun main() {
    fun buildRootDirectory(input: List<String>): Directory {
        val root = Directory("/")
        var currentDirectory = root
        input.drop(1).forEach { command ->
            when {
                command.startsWith("$ cd ..") -> currentDirectory = currentDirectory.parent!!
                command.startsWith("$ cd ") -> currentDirectory = currentDirectory.subDirectories.first { it.name == command.split(" ")[2] }
                command.startsWith("dir ") -> currentDirectory.subDirectories.add(Directory(name = command.split(" ")[1], parent = currentDirectory))
                !command.startsWith("$") -> currentDirectory.files.add(File(size = command.split(" ")[0].toInt(), name = command.split(" ")[1]))
            }
        }
        return root
    }
    fun part1(input: List<String>): Int {
        val root = buildRootDirectory(input)
        return root.allSubDirectories().map { it.size() }.filter { it < 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val root = buildRootDirectory(input)
        val remainingRequiredSpace = 30000000 - (70000000 - root.size())
        return root.allSubDirectories().map { it.size() }.filter { it > remainingRequiredSpace }.sorted().first()
    }

    val input = readInput("Day07")
    val testInput = readInput("Day07_test")
    // PART 1
    check(part1(testInput) == 95437)
    println(part1(input))

    // PART 2
    check(part2(testInput) == 24933642)
    println(part2(input))
}

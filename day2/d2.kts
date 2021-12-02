import java.io.File

println(File("i1").useLines { it.toList() }
    .map { it.split(" ") }
    .map { Pair(it[0], it[1].toInt()) }
    .map {
        if (it.first == "up")
            Pair("down", -it.second)
        else
            it
    }
    .fold(Triple(0, 0, 0)) { acc, pair ->
        if (pair.first == "forward")
            Triple(acc.first + pair.second, acc.second + acc.third * pair.second, acc.third)
        else
            Triple(acc.first, acc.second, acc.third + pair.second)
    }
    .toList().take(2).fold(1) { a, v -> a * v })

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
    .groupingBy { (key, _) -> key }
    .fold(0) { acc, (_, c) -> acc + c }
    .values.fold(1) {acc, i -> i * acc})

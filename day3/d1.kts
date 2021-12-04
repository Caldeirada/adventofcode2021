import java.io.File
import kotlin.math.pow

println(File("i1").useLines { it.toList() }
    .flatMap { it.withIndex() }
    .groupBy { it.index }
    .values.map { list -> list.map { it.value.toString() } }
    .map { it.groupingBy { i -> i }.eachCount() }
    .map { it.maxByOrNull { i -> i.value }?.key!!.toInt() }
    .reversed()
    .foldIndexed(Pair(0, 0)) { index, acc, i ->
        Pair(
            acc.first + i * (2.0.pow(index).toInt()),
            acc.second + ((i + 1) % 2) * (2.0.pow(index).toInt())
        )
    }
    .toList().fold(1) { acc, i -> i * acc }
)
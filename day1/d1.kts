import java.io.File

var prev = 9999
fun count(c: Int, i: Int): Int {
    return if (i > prev) {
        prev = i
        c + 1
    } else {
        prev = i
        c
    }
}
println(File("i1").useLines { it.toList() }.map { it.toInt() }.fold(0) { c, i -> count(c, i) })
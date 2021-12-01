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
val l = File("i1").useLines { it.toList() }.map { it.toInt() }
var l2 = mutableListOf<List<Int>>()

for (i in 0 until l.size-2) {
    l2.add(l.slice(i..i+2))
}
println(l2.map { it.sum() }.fold(0) { c, i -> count(c, i) })
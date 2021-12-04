import java.io.File

val input = File("i1").useLines { it.toList() }
tailrec fun o2(i: Int = 0, l: List<String>): Int {
    if (l.size == 1) return l.first().toInt(2)
    return o2(i + 1, l.mapIndexed { index, s -> mapOf(s[i] to index) }.groupBy { it.keys }
        .maxWithOrNull { e1, e2 ->
            when {
                e1.value.size > e2.value.size || e1.value.size == e2.value.size && e1.key.contains('1') -> 1
                e1.value.size == e2.value.size && e2.key.contains('1') || e1.value.size < e2.value.size -> -1
                else -> 0
            }
        }?.value!!.flatMap { it.values }.map { l[it] })
}

tailrec fun co2(i: Int = 0, l: List<String>): Int {
    if (l.size == 1) return l.first().toInt(2)
    return co2(i + 1, l.mapIndexed { index, s -> mapOf(s[i] to index) }.groupBy { it.keys }
        .minWithOrNull { e1, e2 ->
            when {
                e1.value.size < e2.value.size || e1.value.size == e2.value.size && e1.key.contains('0') -> -1
                e1.value.size == e2.value.size && e2.key.contains('0') || e1.value.size > e2.value.size -> 1
                else -> 0
            }
        }?.value!!.flatMap { it.values }.map { l[it] })
}
println(o2(l = input) * co2(l = input))
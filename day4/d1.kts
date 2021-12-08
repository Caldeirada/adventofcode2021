import java.io.File

fun bingo(p: Pair<List<Int>, List<List<List<Pair<Int, Boolean>>>>>): Int = bingo(p.second, p.first)

tailrec fun bingo(boards: List<List<List<Pair<Int, Boolean>>>>, numbers: List<Int>): Int {
    val num = numbers[0]
    var rowComplete: Pair<Int, Boolean> = Pair(0, false)
    var colComplete: Pair<Int, Boolean> = Pair(0, false)
    var boardNum = 0
    val newBoards = boards.map { board ->
        board.map { r ->
            r.map {
                if (!it.second) {
                    Pair(it.first, it.first == num)
                } else
                    it
            }
        }
    }

    rowComplete = Pair(0, false)
    colComplete = Pair(0, false)
    newBoards.forEachIndexed { n, board ->
        board.forEachIndexed { i, r ->
            if (!rowComplete.second && !colComplete.second) {
                boardNum = n
                rowComplete = Pair(i, board[i].map { it.second }.all { it })
                r.forEachIndexed { j, _ ->
                    if (!rowComplete.second && !colComplete.second) {
                        boardNum = n
                        colComplete = Pair(j, board.map { b -> b[j].second }.all { it })
                    }
                }
            }
        }
    }

    if (rowComplete.second || colComplete.second) {
        return newBoards[boardNum].flatMap { it.filter { v -> !v.second }.map { v -> v.first } }.sum() * num
    }

    return bingo(newBoards, numbers.subList(1, numbers.size))
}

println(
    bingo(File("i1").bufferedReader().readLines()
        .foldIndexed(Pair(listOf(), listOf())) { i, l, s ->
            if (i == 0) {
                Pair(s.split(",").map { it.toInt() }, l.second)
            } else if (s == "") {
                Pair(l.first, l.second + listOf(emptyList()))
            } else {
                Pair(
                    l.first,
                    l.second.subList(0, l.second.size - 1) + listOf(
                        l.second.last() + listOf(
                            s.split("  ", " ").filter { it != "" }.map { Pair(it.toInt(), false) })
                    )
                )
            }
        })
)

/*
14 10 18 22  2
21 16 15  9 19
17  8 23 26 20
24 11 13  6  5
 4  0 12  3  7
 */
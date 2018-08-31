package aa.lcd

class Lcd(private val horizontalZoom: Int = 1, private val verticalZoom: Int = 1) {
    fun display(int: Int): String {
        return display(int, "\n" * verticalZoom*2)
    }

    private tailrec fun display(int: Int, acc: String): String = when {
        int == 0 -> char(0) concat acc
        int / 10 == 0 -> char(int) concat acc
        else -> display(int / 10, char(int % 10) concat acc)
    }

    private infix fun String.concat(other: String): String =
            (lines() zip other.lines())
                    .map { "${it.first}${it.second}" }
                    .joinToString("\n")

    private fun char(digit: Int): String = (chars[digit] ?: """
                    |   
                    | \/
                    | /\
                """.trimMargin()
            )
            .horizontalZoom()
            .verticalZoom()

    private fun String.horizontalZoom(): String = lines()
            .map { it.toList() }
            .map { "${it[0]}${it[1] * horizontalZoom}${it[2]}" }
            .joinToString("\n")

    private fun String.verticalZoom(): String = lines()
            .mapIndexed { index, line ->  when(index) {
                0 -> line
                else -> stretch(line)
            }}
            .joinToString("\n")

    private fun stretch(line: String): String = Array(verticalZoom) { i ->
        if (i < verticalZoom - 1) line.map { c: Char -> if (c == '_') ' ' else c }.joinToString("")
        else line
    }.joinToString("\n")

    private val chars = mapOf(
            0 to """
            | _ 
            || |
            ||_|
        """.trimMargin(),
            1 to """
            |   
            |  |
            |  |
        """.trimMargin(),
            2 to """
            | _ 
            | _|
            ||_ 
        """.trimMargin(),
            3 to """
            | _ 
            | _|
            | _|
        """.trimMargin(),
            4 to """
            |   
            ||_|
            |  |
        """.trimMargin(),
            5 to """
            | _ 
            ||_ 
            | _|
        """.trimMargin(),
            6 to """
            |   
            ||_ 
            ||_|
        """.trimMargin(),
            7 to """
            | _ 
            |  |
            |  |
        """.trimMargin(),
            8 to """
            | _ 
            ||_|
            ||_|
        """.trimMargin(),
            9 to """
            | _ 
            ||_|
            | _|
        """.trimMargin()
    )
}

private inline operator fun <reified T> T.times(times: Int): String = Array(times) { this }.joinToString("")

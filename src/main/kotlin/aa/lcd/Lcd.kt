package aa.lcd

class Lcd(private val horizontalZoom: Int = 1) {
    fun display(int: Int): String {
        return display(int, """
            |
            |
            |
        """.trimMargin())
    }

    private tailrec fun display(int: Int, acc: String): String = when {
        int == 0 -> """
            | _ 
            || |
            ||_|
        """.trimMargin() concat acc
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

    private fun String.horizontalZoom(): String = lines()
            .map { it.toList() }
            .map { "${it[0]}${it[1] * horizontalZoom}${it[2]}" }
            .joinToString("\n")

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

private operator fun Char.times(times: Int): String = Array(times) { this }.joinToString("")

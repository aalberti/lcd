package aa.lcd

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class LcdTest : StringSpec({
    "0" {
        lcd(0) shouldBe """
            | _ 
            || |
            ||_|
        """.trimMargin()
    }
    "1" {
        lcd(1) shouldBe """
            |   
            |  |
            |  |
        """.trimMargin()
    }
    "2" {
        lcd(2) shouldBe """
            | _ 
            | _|
            ||_ 
        """.trimMargin()
    }
    "11" {
        lcd(11) shouldBe """
            |      
            |  |  |
            |  |  |
        """.trimMargin()
    }
    "all in" {
        lcd(1234567890) shouldBe """
            |    _  _     _     _  _  _  _ 
            |  | _| _||_||_ |_   ||_||_|| |
            |  ||_  _|  | _||_|  ||_| _||_|
        """.trimMargin()
    }
})

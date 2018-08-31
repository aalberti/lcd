package aa.lcd

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class LcdTest : StringSpec({
    "0" {
        Lcd().display(0) shouldBe """
            | _ 
            || |
            ||_|
        """.trimMargin()
    }
    "1" {
        Lcd().display(1) shouldBe """
            |   
            |  |
            |  |
        """.trimMargin()
    }
    "2" {
        Lcd().display(2) shouldBe """
            | _ 
            | _|
            ||_ 
        """.trimMargin()
    }
    "11" {
        Lcd().display(11) shouldBe """
            |      
            |  |  |
            |  |  |
        """.trimMargin()
    }
    "all in" {
        Lcd().display(1234567890) shouldBe """
            |    _  _     _     _  _  _  _ 
            |  | _| _||_||_ |_   ||_||_|| |
            |  ||_  _|  | _||_|  ||_| _||_|
        """.trimMargin()
    }
    "horizontal zoom" {
        Lcd(horizontalZoom = 2).display(94) shouldBe """
            | __     
            ||__||__|
            | __|   |
        """.trimMargin()
    }
    "vertical zoom" {
        Lcd(verticalZoom = 2).display(94) shouldBe """
            | _    
            || || |
            ||_||_|
            |  |  |
            | _|  |
        """.trimMargin()
    }
})

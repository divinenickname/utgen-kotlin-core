package io.github.divinenickname.kotlin.utgen.core.domain.kpoet

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ObjectPropertyTest {

    @Test
    fun objectProperty_goldencase() {
        val actual = ObjectProperty("abc", "Pierdolle").toPropertySpec().toString()

        val expected = "private val obj: abc.pierdolle = Pierdolle()\n"

        actual shouldBe expected
    }
}

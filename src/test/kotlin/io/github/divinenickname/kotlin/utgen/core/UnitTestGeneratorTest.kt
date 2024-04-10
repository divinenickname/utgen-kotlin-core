package io.github.divinenickname.kotlin.utgen.core

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Path

class UnitTestGeneratorTest {

    @Test
    fun stringTest() {
        val path = Path.of("src/test/resources/StringTestCase.kt")

        val actual = UnitTestGenerator().generate(path).toString()
        val expected = File("src/test/resources/StringTestCaseExpected.txt").readText()

        actual shouldBe expected
    }
}

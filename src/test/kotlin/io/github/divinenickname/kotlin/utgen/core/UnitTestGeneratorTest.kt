package io.github.divinenickname.kotlin.utgen.core

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Path

class UnitTestGeneratorTest {

    companion object {
        const val RESOURCE_PATH = "src/test/resources"
    }

    @Test
    fun generate_stringCase() {
        val path = Path.of("src/test/resources/StringTestCase.kt")

        val actual = UnitTestGenerator().generate(path).toString()
        val expected = File("${RESOURCE_PATH}/StringTestCaseExpected.txt").readText()

        actual shouldBe expected
    }

    @Test
    fun generateAll_twoClasses() {
        val path = Path.of("${RESOURCE_PATH}/TwoClassesCase.kt")

        val actual = UnitTestGenerator().generateAll(path)

        actual.size shouldBe 2
        actual.map { it.name } shouldContainAll setOf("FizzClassTest", "BuzzClassTest")
    }

    @Test
    fun generateAll_twoClassesBody() {
        val path = Path.of("${RESOURCE_PATH}/TwoClassesCase.kt")

        val actual = UnitTestGenerator().generateAll(path).associateBy { it.name }

        actual["FizzClassTest"].toString() shouldBe File("${RESOURCE_PATH}/TwoClassesCase_FizzExpected.txt").readText()
        actual["BuzzClassTest"].toString() shouldBe File("${RESOURCE_PATH}/TwoClassesCase_BuzzExpected.txt").readText()
    }

    @Test
    fun generateAll_requireStmt() {
        val path = Path.of("${RESOURCE_PATH}/RequireStmt.kt")

        val actual = UnitTestGenerator().generateAll(path)

        println()
    }
}

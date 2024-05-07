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
    fun generateAll_checkExternalImports() {
        val path = Path.of("${RESOURCE_PATH}/ClassWithExternalImports.txt")

        val actual = UnitTestGenerator().generateAll(path).first().toString()
        val expected = """
            package io.github.divinenickname.kotlin.utgen.core

            import org.example.MyClass
            import org.junit.jupiter.api.Assertions
            import org.junit.jupiter.api.Test

            internal class ClassWithExternalImportsTest {
              @Test
              public fun method_notThrowTest() {
                val obj = ClassWithExternalImports()

                val expected = MyClass()
                val actual = obj.method()

                Assertions.assertEquals(expected, actual)
              }
            }
            
        """.trimIndent()

        actual shouldBe expected
    }

    @Test
    fun generateAll_requireStmt() {
        val path = Path.of("${RESOURCE_PATH}/RequireStmt.kt")

        val actual = UnitTestGenerator().generateAll(path).first().toString()
        val expected = """
            package io.github.divinenickname.kotlin.utgen.core

            import org.junit.jupiter.api.Assertions
            import org.junit.jupiter.api.Test

            internal class RequireStmtTest {
              @Test
              public fun voidMethod_notThrowTest() {
                val obj = RequireStmt()

                Assertions.assertDoesNotThrow { obj.voidMethod() }
              }

              @Test
              public fun voidMethod_requireStmt1() {
                val obj = RequireStmt()

                TODO('require(1==1)')
                Assertions.assertThrows(IllegalArgumentException::class.java) { obj.voidMethod() }
              }
            }
            
        """.trimIndent()

        actual shouldBe expected
    }
}

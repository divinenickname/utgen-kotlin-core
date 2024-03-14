package io.github.divinenickname.kotlin.utgen.core

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File

internal class UnitTestGeneratorTest {
    private val generator = UnitTestGenerator()

    @Test
    fun example() {
        val expected = """
            package io.github.divinenickname.kotlin.utgen.core

            import org.junit.jupiter.api.Assertions
            import org.junit.jupiter.api.Test

            internal class TestClassTest {
              private val obj: TestClass = TestClass()

              @Test
              public fun voidMethod_goldencase() {
                TODO("Implement")
                Assertions.assertDoesNotThrow { obj.voidMethod() }
              }

              @Test
              public fun nonVoidMethod_goldencase() {
                TODO("Implement")
                val expected = String()
                val actual = obj.nonVoidMethod()
            
                Assertions.assertEquals(expected, actual)
              }

              @Test
              public fun publicScopeMethod_goldencase() {
                TODO("Implement")
                Assertions.assertDoesNotThrow { obj.publicScopeMethod() }
              }
            }
            
        """.trimIndent()

        val actual = File("src/main/kotlin/io/github/divinenickname/kotlin/utgen/core/TestClass.kt")
            .toPath().let(generator::generate)

        actual.toString() shouldBe expected
    }
}

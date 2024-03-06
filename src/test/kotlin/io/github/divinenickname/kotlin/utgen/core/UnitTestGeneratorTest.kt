package io.github.divinenickname.kotlin.utgen.core

import com.squareup.kotlinpoet.ClassName
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File

internal class UnitTestGeneratorTest {
    private val generator = UnitTestGenerator(
        ClassName("org.ilinykh.kotlin.utgen", "TestClass"),
        setOf("voidMethod", "nonVoidMethod"),
    )

    @Test
    fun example() {
        val expected = """
            package io.github.divinenickname.kotlin.utgen.core

            import org.junit.jupiter.api.Test

            internal class TestClassTest {
              private val obj: TestClassTest = TestClass()

              @Test
              public fun voidMethod_goldencase() {
                TODO("Implement")
                val actual = obj.voidMethod()
              }

              @Test
              public fun nonVoidMethod_goldencase() {
                TODO("Implement")
                val actual = obj.nonVoidMethod()
              }

              @Test
              public fun publicScopeMethod_goldencase() {
                TODO("Implement")
                val actual = obj.publicScopeMethod()
              }
            }
            
        """.trimIndent()

        val actual = File("src/main/kotlin/io/github/divinenickname/kotlin/utgen/core/TestClass.kt")
            .toPath().let(generator::generate)

        actual.toString() shouldBe expected
    }
}

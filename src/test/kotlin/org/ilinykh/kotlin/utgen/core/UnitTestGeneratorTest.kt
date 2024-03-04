package org.ilinykh.kotlin.utgen.core

import com.squareup.kotlinpoet.ClassName
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class UnitTestGeneratorTest {
    private val generator = UnitTestGenerator(
        ClassName("org.ilinykh.kotlin.utgen", "TestClass"),
        setOf("voidMethod", "nonVoidMethod"),
    )

    @Test
    fun example() {
        val expected = """
            package org.ilinykh.kotlin.utgen

            import org.junit.jupiter.api.Test

            internal class TestClassTest {
              private val obj: TestClass = TestClass()

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
            }
            
        """.trimIndent()

        val actual = generator.generate().toString()
        actual shouldBe expected
    }
}

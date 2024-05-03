package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ReturnPrimitiveTestCasesTest {

    private val objProperty = ObjectProperty("org.example", "MyClass")

    @ParameterizedTest
    @ValueSource(strings = ["Byte", "Short", "Int", "Long", "Float", "Double", "Char", "String", "Byte?"])
    fun canApply_isTrue(returnValue: String) {
        val obj = ReturnPrimitiveTestCases(objProperty, Method("primitive", returnValue))

        val actual = obj.canApply()

        Assertions.assertTrue(actual)
    }

    @Test
    fun canApply_isFalse() {
        val obj = ReturnPrimitiveTestCases(objProperty, Method("nonPrimitive", "Any"))

        val actual = obj.canApply()

        Assertions.assertFalse(actual)
    }

    @Test
    fun funSpecsTest() {
        val obj = ReturnPrimitiveTestCases(objProperty, Method("primitive", "Byte"))

        val expected = setOf(
            FunSpec.builder("primitiveTest")
                .addAnnotation(Test::class)
                .addCode(CodeBlock.of("val obj = MyClass()\n\n"))
                .addCode("""
                    val expected: Byte = TODO('byte')
                    val actual = obj.primitive()
                    
                    Assertions.assertEquals(expected, actual)""".trimIndent().let(CodeBlock::of))
                .build()
        )
        val actual = obj.funSpecs()

        Assertions.assertEquals(expected, actual)
    }
}

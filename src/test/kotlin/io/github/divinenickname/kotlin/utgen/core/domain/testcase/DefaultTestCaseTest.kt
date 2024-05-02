package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class DefaultTestCaseTest {

    private val objProperty = ObjectProperty("org.example", "MyClass")


    @Test
    fun canApply_isTrue() {
        val obj = DefaultTestCase(objProperty, Method("primitive", "Object"))

        val actual = obj.canApply()

        Assertions.assertTrue(actual)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Byte", "Short", "Int", "Long", "Float", "Double", "Char", "Boolean", "String", "Unit", "Byte?"])
    fun canApply_isFalse(returnType: String) {
        val obj = DefaultTestCase(objProperty, Method("primitive", returnType))

        val actual = obj.canApply()

        Assertions.assertFalse(actual)
    }

    @Test
    fun funSpecsTest() {
        val obj = DefaultTestCase(objProperty, Method("primitive", "Object"))

        val expected = setOf(
            FunSpec.builder("primitive_notThrowTest")
                .addAnnotation(Test::class)
                .addCode(CodeBlock.of("val obj = MyClass()\n\n"))
                .addCode("""
            val expected = Object()
            val actual = obj.primitive()
            
            Assertions.assertEquals(expected, actual)
        """.trimIndent().let(CodeBlock::of))
                .build()
        )
        val actual = obj.funSpecs()

        Assertions.assertEquals(expected, actual)
    }
}

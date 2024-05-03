package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ReturnBooleanTestCasesTest {
    private val objProperty = ObjectProperty("org.example", "MyClass")

    @ParameterizedTest
    @ValueSource(strings = ["Boolean", "Boolean?"])
    fun canApply_isTrue(returnValue: String) {
        val obj = ReturnBooleanTestCases(objProperty, Method("methodName", returnValue))

        val actual = obj.canApply()

        Assertions.assertTrue(actual)
    }

    @Test
    fun canApply_isFalse() {
        val obj = ReturnBooleanTestCases(objProperty, Method("methodName", "Any"))

        val actual = obj.canApply()

        Assertions.assertFalse(actual)
    }

    @Test
    fun funSpecsTest() {
        val obj = ReturnBooleanTestCases(objProperty, Method("methodName", "Boolean"))

        val expected = setOf(
            FunSpec.builder("methodName_isTrue")
                .addAnnotation(Test::class)
                .addCode(CodeBlock.of("val obj = MyClass()\n\n"))
                .addCode("""
                    val actual = obj.methodName()
                    
                    Assertions.assertTrue(actual)""".trimIndent().let(CodeBlock::of))
                .build(),

            FunSpec.builder("methodName_isFalse")
                .addAnnotation(Test::class)
                .addCode(CodeBlock.of("val obj = MyClass()\n\n"))
                .addCode("""
                    val actual = obj.methodName()
                    
                    Assertions.assertFalse(actual)""".trimIndent().let(CodeBlock::of))
                .build()
        )
        val actual = obj.funSpecs()

        Assertions.assertEquals(expected, actual)
    }
}

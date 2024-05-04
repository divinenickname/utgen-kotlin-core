package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class RequireTestCasesTest {

    private val objProperty = ObjectProperty("org.example", "MyClass")

    @Test
    fun canApply_isTrue() {
        val obj = RequireTestCases(objProperty, Method("methodName", requireExpression = listOf("1==1")))

        val actual = obj.canApply()

        Assertions.assertTrue(actual)
    }

    @Test
    fun canApply_isFalse() {
        val obj = RequireTestCases(objProperty, Method("methodName"))

        val actual = obj.canApply()

        Assertions.assertFalse(actual)
    }

    @Test
    fun funSpecsTest() {
        val obj = RequireTestCases(objProperty, Method("methodName", requireExpression = listOf("1==1", "false")))

        val expected = setOf(
            FunSpec.builder("methodName_requireStmt1")
                .addAnnotation(Test::class)
                .addCode(CodeBlock.of("val obj = MyClass()\n\n"))
                .addCode("""
                    TODO('require(1==1)')
                    Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }""".trimIndent().let(CodeBlock::of))
                .build(),
            FunSpec.builder("methodName_requireStmt2")
                .addAnnotation(Test::class)
                .addCode(CodeBlock.of("val obj = MyClass()\n\n"))
                .addCode("""
                    TODO('require(false)')
                    Assertions.assertThrows(IllegalArgumentException::class.java) { obj.result() }""".trimIndent().let(CodeBlock::of))
                .build()
        )
        val actual = obj.funSpecs()

        println(actual)

        Assertions.assertEquals(expected, actual)
    }
}

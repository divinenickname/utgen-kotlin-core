package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class DoesNotThrowTestCaseTest {

    private val objProperty = ObjectProperty("org.example", "MyClass")

    @Test
    fun canApply_isTrue() {
        val obj = DoesNotThrowTestCase(objProperty, Method("methodName", "Unit"))

        val actual = obj.canApply()

        Assertions.assertTrue(actual)
    }

    @Test
    fun canApply_isFalse() {
        val obj = DoesNotThrowTestCase(objProperty, Method("methodName", "String"))

        val actual = obj.canApply()

        Assertions.assertFalse(actual)
    }

    @Test
    fun funSpecsTest() {
        val obj = DoesNotThrowTestCase(objProperty, Method("methodName", "Unit"))

        val expected = setOf(
            FunSpec.builder("methodName_notThrowTest")
                .addAnnotation(Test::class)
                .addCode(CodeBlock.of("val obj = MyClass()\n\n"))
                .addCode("Assertions.assertDoesNotThrow { obj.methodName() }".let(CodeBlock::of))
                .build()
        )
        val actual = obj.funSpecs()

        Assertions.assertEquals(expected, actual)
    }
}

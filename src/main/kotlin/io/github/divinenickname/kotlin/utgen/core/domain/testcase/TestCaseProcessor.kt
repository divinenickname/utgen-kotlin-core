package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import org.junit.jupiter.api.Test

/**
 * The heart of test-case generation. If you add new test-case you have to add it to [TestCaseProcessor.testCases] set.
 */
class TestCaseProcessor(private val objProperty: ObjectProperty, private val method: Method) {
    private val testCases = setOf(
        ::ReturnNullableTestCase,
        ::ReturnBooleanTestCases,
        ::ReturnPrimitiveTestCases,
        ::DoesNotThrowTestCase,
        ::DefaultTestCase,
    ).map { it(objProperty, method) }

    /**
     * Generate code blocks for valid cases. O(n) complexity.
     */
    fun generateCodeBlocks(): Set<FunSpec> = testCases.map { it.funSpecs() }.flatten()
        .takeIf { it.isNotEmpty() }?.toSet()
        ?: setOf(defaultFunSpec())

    /**
     * The default test method in case no other cases were applied.
     */
    private fun defaultFunSpec(): FunSpec = FunSpec.builder(method.name)
        .addAnnotation(Test::class)
        .addCode(CodeBlock.of("val obj = ${objProperty.toPropertySpec().initializer}\n\n"))
        .addCode(
            """
            val expected = ${method.returnValue()}()
            val actual = ${objProperty.toPropertySpec().name}.${method.name}()
            
            Assertions.assertEquals(expected, actual)
        """.trimIndent().let(CodeBlock::of)
        )
        .build()


}

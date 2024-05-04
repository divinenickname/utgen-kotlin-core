package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

/**
 * The heart of test-case generation. If you add new test-case you have to add it to [TestCaseProcessor.testCases] set.
 */
class TestCaseProcessor(
    private val objProperty: ObjectProperty,
    private val method: Method
) {
    private val testCases = setOf(
        ::ReturnNullableTestCase,
        ::ReturnBooleanTestCases,
        ::ReturnPrimitiveTestCases,
        ::DoesNotThrowTestCase,
        ::DefaultTestCase,
        ::RequireTestCases,
    ).map { it(objProperty, method) }

    /**
     * Generate code blocks for valid cases. O(n) complexity.
     */
    fun generateCodeBlocks(): Set<FunSpec> = testCases.map { it.funSpecs() }.flatten()
        .toSet()

}

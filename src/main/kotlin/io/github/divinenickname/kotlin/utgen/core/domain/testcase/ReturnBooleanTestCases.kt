package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

/**
 * Boolean return type test-cases.
 */
class ReturnBooleanTestCases(
    private val objProperty: ObjectProperty,
    private val method: Method,
) : TestCase(objProperty, method) {

    override fun canApply(): Boolean = method.returnValue.className() == "Boolean"

    override fun funSpecs(): Set<FunSpec> {
        if (!canApply()) return emptySet()

        return setOf(testCase("True"), testCase("False"))
    }

    private fun testCase(boolean: String) = TestMethod(
        testMethodName = "${method.name}_is$boolean",
        propertySpec = objProperty.toPropertySpec(),
        codeBlock = """
            val actual = ${objProperty.toPropertySpec().name}.${method.name}()
    
            Assertions.assert$boolean(actual)
        """.trimIndent().let(CodeBlock::of)
    ).funSpec()

}

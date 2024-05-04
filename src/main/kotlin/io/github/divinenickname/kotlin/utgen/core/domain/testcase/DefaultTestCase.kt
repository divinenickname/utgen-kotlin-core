package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

/**
 * This assertion chain serves as the base case, utilized when no other applicable case is available.
 * Additionally, it functions as an extra case for certain types.
 */
class DefaultTestCase(
    private val objProperty: ObjectProperty,
    private val method: Method,
): TestCase(objProperty, method) {

    private val primitives = setOf("Byte", "Short", "Int", "Long", "Float", "Double", "Char", "Boolean", "String")

    private fun isUnitType() = method.returnValue.className() == "Unit"
    private fun isNullable() = method.returnValue.isNullable()
    private fun isPrimitive() = primitives.contains(method.returnValue.className())

    override fun canApply(): Boolean = !isUnitType() && !isPrimitive() && !(isPrimitive() && isNullable())

    override fun funSpecs(): Set<FunSpec> {
        if (!canApply()) return emptySet()

        return TestMethod(
            testMethodName = "${method.name}_notThrowTest",
            propertySpec = objProperty.toPropertySpec(),
            codeBlock = """
            val expected = ${method.returnValue.className()}()
            val actual = ${objProperty.toPropertySpec().name}.${method.name}()
            
            Assertions.assertEquals(expected, actual)
        """.trimIndent().let(CodeBlock::of)
        ).funSpec().let { setOf(it) }
    }
}

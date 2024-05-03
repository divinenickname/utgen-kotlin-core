package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

class ReturnPrimitiveTestCases(
    private val objProperty: ObjectProperty,
    private val method: Method,
) : TestCase(objProperty, method) {

    private val primitives = setOf("Byte", "Short", "Int", "Long", "Float", "Double", "Char", "String")

    override fun canApply(): Boolean = primitives.contains(method.returnValue())

    override fun funSpecs(): Set<FunSpec> {
        if (!canApply()) return emptySet()

        return TestMethod(
            testMethodName = "${method.name}Test",
            propertySpec = objProperty.toPropertySpec(),
            codeBlock = """
            val expected: ${method.returnValue()} = TODO('${method.returnValue().lowercase()}')
            val actual = ${objProperty.toPropertySpec().name}.${method.name}()
            
            Assertions.assertEquals(expected, actual)
        """.trimIndent().let(CodeBlock::of)
        ).funSpec().let { setOf(it) }
    }
}

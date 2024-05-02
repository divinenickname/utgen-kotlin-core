package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
/**
 * If method returns null - we should to check it to nullability
 */
class ReturnNullableTestCase(
    private val objProperty: ObjectProperty,
    private val method: Method,
) : TestCase(objProperty, method) {

    override fun canApply(): Boolean = method.isNullable()

    private fun execute(): CodeBlock = CodeBlock
        .of("Assertions.assertNull( ${objProperty.toPropertySpec().name}.${method.name}() )")

    override fun funSpecs(): Set<FunSpec> {
        if (!canApply()) return emptySet()

        return TestMethod(
            testMethodName = "${method.name}_isNullTest",
            propertySpec = objProperty.toPropertySpec(),
            codeBlock = execute()
        ).funSpec().let { setOf(it) }
    }
}

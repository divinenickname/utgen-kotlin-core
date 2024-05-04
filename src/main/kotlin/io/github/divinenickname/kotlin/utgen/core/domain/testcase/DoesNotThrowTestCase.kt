package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

/**
 * If method returns nothing - it shouldn't throw anything. Golden case for [Unit] return type
 */
class DoesNotThrowTestCase(
    private val objProperty: ObjectProperty,
    private val method: Method,
) : TestCase(objProperty, method) {

    override fun canApply(): Boolean = method.returnValue.className() == "Unit"

    override fun funSpecs(): Set<FunSpec> {
        if (!canApply()) return emptySet()

        return TestMethod(
            testMethodName = "${method.name}_notThrowTest",
            propertySpec = objProperty.toPropertySpec(),
            codeBlock = CodeBlock.of("Assertions.assertDoesNotThrow { ${objProperty.toPropertySpec().name}.${method.name}() }")
        ).funSpec().let { setOf(it) }
    }
}

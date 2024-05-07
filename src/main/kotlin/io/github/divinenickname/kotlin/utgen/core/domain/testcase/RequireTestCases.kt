package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

class RequireTestCases(
    private val objProperty: ObjectProperty,
    private val method: Method,
) : TestCase(objProperty, method) {

    override fun canApply(): Boolean = method.requireExpressions().isNotEmpty()

    override fun funSpecs(): Set<FunSpec> = method.requireExpressions()
        .mapIndexed { index, expression ->
            TestMethod(
                testMethodName = "${method.name}_requireStmt${index+1}",
                propertySpec = objProperty.toPropertySpec(),
                codeBlock = """
                    TODO('require($expression)')
                    Assertions.assertThrows(IllegalArgumentException::class.java) { obj.${method.name}() }
                """.trimIndent().let(CodeBlock::of)
            ).funSpec()
        }.toSet()
}

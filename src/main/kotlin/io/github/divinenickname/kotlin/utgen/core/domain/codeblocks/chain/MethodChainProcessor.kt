package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertActualCodeBlock
import org.junit.jupiter.api.Test

class MethodChainProcessor(private val objProperty: PropertySpec, private val method: Method) {
    private val codeChain = setOf(
        AssertDoesNotThrowChain(),
        AssertPrimitiveMethodChain(),
        AssertNullChain()
    )

    fun generateCodeBlocks(): Set<FunSpec> = codeChain
        .takeWhile { it.isValid(method) }
        .map(this::funSpec)
        .takeIf { it.isNotEmpty() }?.toSet()
        ?: setOf(defaultFunSpec())


    private fun funSpec(codeChain: CodeChain): FunSpec {
        return FunSpec.builder(codeChain.testMethodName(method))
            .addAnnotation(Test::class)
            .addCode(CodeBlock.of("val obj = ${objProperty.initializer}\n\n"))
            .addCode(codeChain.execute(objProperty, method))
            .build()
    }

    private fun defaultFunSpec(): FunSpec = FunSpec.builder(method.name)
        .addAnnotation(Test::class)
        .addCode(CodeBlock.of("val obj = ${objProperty.initializer}\n\n"))
        .addCode(AssertActualCodeBlock(objProperty, method).codeBlock())
        .build()
}

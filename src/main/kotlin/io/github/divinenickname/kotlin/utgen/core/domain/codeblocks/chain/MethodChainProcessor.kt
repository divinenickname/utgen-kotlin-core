package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.DefaultAssertionCodeBlock
import org.junit.jupiter.api.Test

/**
 * The heart of chain applying. If you add new chain you have to add it to [MethodChainProcessor.codeChain] set.
 */
class MethodChainProcessor(private val objProperty: PropertySpec, private val method: Method) {
    private val codeChain = setOf(
        ::AssertDoesNotThrowChain,
        ::AssertPrimitiveMethodChain,
        ::AssertNullChain,
        ::AssertTrueChain,
        ::AssertFalseChain,
        ::DefaultAssertionChain,
    ).map { it(objProperty, method) }

    /**
     * Generate code blocks for valid cases. O(n) complexity.
     */
    fun generateCodeBlocks(): Set<FunSpec> = codeChain
        .filter { it.isValid() }
        .map(this::funSpec)
        .takeIf { it.isNotEmpty() }?.toSet()
        ?: setOf(defaultFunSpec())


    /**
     * Create test method with annotation and object initialization.
     */
    private fun funSpec(codeChain: CodeChain): FunSpec {
        return FunSpec.builder(codeChain.testMethodName())
            .addAnnotation(Test::class)
            .addCode(CodeBlock.of("val obj = ${objProperty.initializer}\n\n"))
            .addCode(codeChain.execute())
            .build()
    }

    /**
     * The default test method in case no other cases were applied.
     */
    private fun defaultFunSpec(): FunSpec = FunSpec.builder(method.name)
        .addAnnotation(Test::class)
        .addCode(CodeBlock.of("val obj = ${objProperty.initializer}\n\n"))
        .addCode(DefaultAssertionCodeBlock(objProperty, method).codeBlock())
        .build()
}

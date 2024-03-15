package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertActualCodeBlock

class MethodChainProcessor(private val objProperty: PropertySpec, private val method: Method) {
    private val codeChain = setOf(
        AssertDoesNotThrowChain(),
        AssertPrimitiveMethodChain(),
        AssertNullChain()
    )

    fun generateCodeBlocks(): Set<CodeBlock> {
        return codeChain
            .takeWhile { it.isValid(method) }
            .map { it.execute(objProperty, method) }
            .takeIf { it.isNotEmpty() }?.toSet()
            ?: setOf(AssertActualCodeBlock(objProperty, method).codeBlock())
    }
}

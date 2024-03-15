package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertPrimitiveMethodCodeBlock

class AssertPrimitiveMethodChain : CodeChain {
    private val primitives = setOf("Byte", "Short", "Int", "Long", "Float", "Double", "Char", "Boolean")

    override fun isValid(method: Method): Boolean = primitives.contains(method.returnValue)

    override fun execute(objProperty: PropertySpec, method: Method): CodeBlock =
        AssertPrimitiveMethodCodeBlock(objProperty, method).codeBlock()
}

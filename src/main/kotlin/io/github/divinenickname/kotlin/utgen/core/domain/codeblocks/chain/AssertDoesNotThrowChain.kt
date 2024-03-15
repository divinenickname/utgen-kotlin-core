package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertDoesNotThrowCodeBlock

class AssertDoesNotThrowChain : CodeChain {
    override fun isValid(method: Method): Boolean = method.returnValue() == "Unit"

    override fun execute(objProperty: PropertySpec, method: Method): CodeBlock =
        AssertDoesNotThrowCodeBlock(objProperty, method).codeBlock()
}

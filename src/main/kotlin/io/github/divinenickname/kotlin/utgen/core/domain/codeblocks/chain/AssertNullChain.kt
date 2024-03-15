package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertNullCodeBlock

class AssertNullChain : CodeChain {
    override fun isValid(method: Method): Boolean = method.isNullable()

    override fun execute(objProperty: PropertySpec, method: Method): CodeBlock =
        AssertNullCodeBlock(objProperty, method).codeBlock()

    override fun testMethodName(method: Method): String = "${method.name}_isNullTest"
}

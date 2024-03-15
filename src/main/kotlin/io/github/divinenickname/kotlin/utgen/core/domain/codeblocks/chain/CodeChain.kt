package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method

interface CodeChain {
    fun isValid(method: Method): Boolean
    fun execute(objProperty: PropertySpec, method: Method): CodeBlock
}

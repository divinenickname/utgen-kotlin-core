package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method

class AssertNullCodeBlock(private val objProperty: PropertySpec, private val method: Method): CodeBlockObj {
    override fun codeBlock(): CodeBlock =  CodeBlock
        .of("Assertions.assertNull( ${objProperty.name}.${method.name}() )")
}

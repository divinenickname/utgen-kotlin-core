package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method

class UnitAssertionBlock(private val objProperty: PropertySpec, private val method: Method) : CodeBlockObj {

    override fun codeBlock(): CodeBlock = if (method.returnValue == "Unit") {
        "Assertions.assertDoesNotThrow { ${objProperty.name}.${method.name}() }"
    } else {
        "val actual = ${objProperty.name}.${method.name}()"
    }
        .let(CodeBlock::of)

}

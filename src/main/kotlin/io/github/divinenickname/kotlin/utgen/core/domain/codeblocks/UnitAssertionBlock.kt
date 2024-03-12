package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method

@Deprecated("Use [AssertCodeBlockStrategy] instead")
class UnitAssertionBlock(private val objProperty: PropertySpec, private val method: Method) : CodeBlockObj {

    override fun codeBlock(): CodeBlock = if (method.returnValue == "Unit") {
        "Assertions.assertDoesNotThrow { ${objProperty.name}.${method.name}() }"
    } else {
        "val actual = ${objProperty.name}.${method.name}()"
    }
        .let(CodeBlock::of)

}

class AssertDoesNotThrowCodeBlock(private val objProperty: PropertySpec, private val method: Method) : CodeBlockObj {
    override fun codeBlock(): CodeBlock = CodeBlock
        .of("Assertions.assertDoesNotThrow { ${objProperty.name}.${method.name}() }")
}

class AssertActualCodeBlock(private val objProperty: PropertySpec, private val method: Method) : CodeBlockObj {
    override fun codeBlock(): CodeBlock = CodeBlock
        .of("val actual = ${objProperty.name}.${method.name}()")
}

class AssertCodeBlockStrategy(private val objProperty: PropertySpec, private val method: Method) : CodeBlockObj {
    override fun codeBlock(): CodeBlock = if (method.returnValue == "Unit") {
        AssertDoesNotThrowCodeBlock(objProperty, method)
    } else {
        AssertActualCodeBlock(objProperty, method)
    }.codeBlock()
}

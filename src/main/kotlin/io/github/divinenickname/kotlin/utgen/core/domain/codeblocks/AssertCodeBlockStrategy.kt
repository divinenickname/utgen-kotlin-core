package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method

class AssertCodeBlockStrategy(private val objProperty: PropertySpec, private val method: Method) {

    private val primitives = setOf("Byte", "Short", "Int", "Long", "Float", "Double", "Char", "Boolean")

    fun getStrategy(): CodeBlockObj = when {
        primitives.contains(method.returnValue) -> AssertPrimitiveMethodCodeBlock(objProperty, method)
        method.returnValue == "Unit" -> AssertDoesNotThrowCodeBlock(objProperty, method)
        else -> AssertActualCodeBlock(objProperty, method)
    }
}

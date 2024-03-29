package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.DefaultAssertionCodeBlock


class DefaultAssertionChain(
    objProperty: PropertySpec,
    val method: Method
) : CodeChain(
    methodNamePostfix = "Test",
    codeBlockClass = DefaultAssertionCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {

    private val primitives = setOf("Byte", "Short", "Int", "Long", "Float", "Double", "Char", "Boolean")

    override fun isValid(): Boolean = !isUnitType() && !isPrimitive() && !(isPrimitive() && isNullable())

    private fun isUnitType() = method.returnValue() == "Unit"
    private fun isNullable() = method.isNullable()
    private fun isPrimitive() = primitives.contains(method.returnValue())
}

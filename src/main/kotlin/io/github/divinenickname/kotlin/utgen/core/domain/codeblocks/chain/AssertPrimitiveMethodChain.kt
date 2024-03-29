package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertPrimitiveMethodCodeBlock

class AssertPrimitiveMethodChain(
    objProperty: PropertySpec,
    val method: Method
) : CodeChain(
    methodNamePostfix = "Test",
    codeBlockClass = AssertPrimitiveMethodCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {
    private val primitives = setOf("Byte", "Short", "Int", "Long", "Float", "Double", "Char")

    override fun isValid(): Boolean = primitives.contains(method.returnValue())
}

package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertDoesNotThrowCodeBlock

/**
 * If method returns nothing - it shouldn't throw anything. Golden case for UNIT return type
 */
class AssertDoesNotThrowChain(
    objProperty: PropertySpec,
    val method: Method
) : CodeChain(
    methodNamePostfix = "_notThrowTest",
    codeBlockClass = AssertDoesNotThrowCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {
    override fun isValid(): Boolean = method.returnValue() == "Unit"
}

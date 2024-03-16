package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertDoesNotThrowCodeBlock

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

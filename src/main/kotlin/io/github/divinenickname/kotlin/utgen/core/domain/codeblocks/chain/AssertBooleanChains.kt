package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertTrueCodeBlock

class AssertTrueChain(
    objProperty: PropertySpec,
    val method: Method
) : CodeChain(
    methodNamePostfix = "_isTrue",
    codeBlockClass = AssertTrueCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {
    override fun isValid(): Boolean = method.returnValue() == "Boolean"
}

class AssertFalseChain(
    objProperty: PropertySpec,
    val method: Method
) : CodeChain(
    methodNamePostfix = "_isFalse",
    codeBlockClass = AssertTrueCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {
    override fun isValid(): Boolean = method.returnValue() == "Boolean"
}

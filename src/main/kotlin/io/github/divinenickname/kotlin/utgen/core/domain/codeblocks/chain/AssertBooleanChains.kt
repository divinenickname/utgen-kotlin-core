package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertFalseCodeBlock
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertTrueCodeBlock
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.CodeBlockObj

/**
 * Boolean codeChain "true" case.
 *
 * @param objProperty object to test. It is part of given.
 * @param method method to test.
 */
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

/**
 * Boolean codeChain "false" case.
 *
 * @param objProperty object to test. It is part of given.
 * @param method method to test.
 */
class AssertFalseChain(
    objProperty: PropertySpec,
    val method: Method,
) : CodeChain(
    methodNamePostfix = "_isFalse",
    codeBlockClass = AssertFalseCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {
    override fun isValid(): Boolean = method.returnValue() == "Boolean"
}

package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertNullCodeBlock

class AssertNullChain(
    objProperty: PropertySpec,
    val method: Method
) : CodeChain(
    methodNamePostfix = "_isNullTest",
    codeBlockClass = AssertNullCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {
    override fun isValid(): Boolean = method.isNullable()
}

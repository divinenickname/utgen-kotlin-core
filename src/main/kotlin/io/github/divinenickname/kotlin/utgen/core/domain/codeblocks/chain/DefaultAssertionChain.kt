package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.DefaultAssertionCodeBlock

/**
 * Logic based on CNF 'Conjunctive normal form'. Truth table:
 *
 * | Unit (x) | Nullable (y) | Boolean (z) | Fun | CNF          |
 * |----------|--------------|-------------|-----|--------------|
 * | 1        | 0            | 0           | 0   | !x OR y OR z |
 * | 0        | 1            | 1           | 0   | !y OR !z     |
 * | 0        | 0            | 1           | 0   | y OR !z      |
 */
class DefaultAssertionChain(
    objProperty: PropertySpec,
    val method: Method
) : CodeChain(
    methodNamePostfix = "Test",
    codeBlockClass = DefaultAssertionCodeBlock::class.java,
    objProperty = objProperty,
    method = method
) {

    override fun isValid(): Boolean = (!x() || y() ||z()) && (!y() || !z()) && (y() || !z())

    private fun x() = method.returnValue() == "Unit"
    private fun y() = method.isNullable()
    private fun z() = method.returnValue() == "Boolean"
}

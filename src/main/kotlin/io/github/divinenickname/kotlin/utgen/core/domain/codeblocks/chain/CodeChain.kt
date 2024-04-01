package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.CodeBlockObj
import kotlin.reflect.KFunction

/**
 * CodeChain - is kind of chain of responsibility pattern provides a mechanism to decide what type of code-generation should apply.
 * @param methodNamePostfix unique postfix used in methodName. In my opinion this postfix should be start with "_" e.g. _isFalse.
 * @param codeBlockClass CodeBlock class used for generating test method.
 * @param objProperty object to test. It is part of given. In test class it should be
 * ```
 * val obj = MyTestClass()
 * ```
 * @param method method to test.
 */
abstract class CodeChain(
    private val methodNamePostfix: String,
    private val codeBlockClass: Class<out CodeBlockObj>,
    private val objProperty: PropertySpec,
    private val method: Method,
) {

    /**
     * @return true if we can apply this chain to source code.
     */
    abstract fun isValid(): Boolean

    /**
     * @return test-case code.
     */
    fun execute(): CodeBlock = codeBlockClass
        .getConstructor(PropertySpec::class.java, Method::class.java)
        .newInstance(objProperty, method)
        .codeBlock()

    /**
     * @return test method name.
     */
    fun testMethodName(): String = "${method.name}$methodNamePostfix"
}

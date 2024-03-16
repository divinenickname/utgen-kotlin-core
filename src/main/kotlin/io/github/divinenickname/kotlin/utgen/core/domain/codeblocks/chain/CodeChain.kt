package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.CodeBlockObj
import kotlin.reflect.KFunction

abstract class CodeChain(
    private val methodNamePostfix: String,
    private val codeBlockClass: Class<out CodeBlockObj>,
    private val objProperty: PropertySpec,
    private val method: Method,
) {
    abstract fun isValid(): Boolean

    fun execute(): CodeBlock = codeBlockClass
        .getConstructor(PropertySpec::class.java, Method::class.java)
        .newInstance(objProperty, method)
        .codeBlock()

    fun testMethodName(): String = "${method.name}$methodNamePostfix"
}

package io.github.divinenickname.kotlin.utgen.core.domain.testclass

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.ImplementBlock
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain.MethodChainProcessor
import org.junit.jupiter.api.Test

class PublicMethodSpec(
    method: Method,
    objectProperty: PropertySpec,
) {

    private val funSpec = FunSpec.builder("${method.name}_goldencase")
        .addAnnotation(Test::class)
        .addCode(ImplementBlock.codeBlock())
        .apply { MethodChainProcessor(objectProperty, method).generateCodeBlocks().forEach(this::addCode) }
        .build()

    fun toSpec(): FunSpec = funSpec
}

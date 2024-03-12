package io.github.divinenickname.kotlin.utgen.core.domain.testclass

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.AssertCodeBlockStrategy
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.ImplementBlock
import org.junit.jupiter.api.Test

class PublicMethodSpec(
    method: Method,
    objectProperty: PropertySpec,
) {

    private val funSpec = FunSpec.builder("${method.name}_goldencase")
        .addAnnotation(Test::class)
        .addCode(ImplementBlock.codeBlock())
        .addCode(AssertCodeBlockStrategy(objectProperty, method).codeBlock())
        .build()

    fun toSpec(): FunSpec = funSpec
}

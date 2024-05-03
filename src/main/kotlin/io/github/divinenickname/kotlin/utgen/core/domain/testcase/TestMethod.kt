package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import org.junit.jupiter.api.Test

class TestMethod(
    private val testMethodName: String,
    private val propertySpec: PropertySpec,
    private val codeBlock: CodeBlock,
) {

    fun funSpec(): FunSpec = FunSpec.builder(testMethodName)
        .addAnnotation(Test::class)
        .addCode(CodeBlock.of("val obj = ${propertySpec.initializer}\n\n"))
        .addCode(codeBlock)
        .build()
}

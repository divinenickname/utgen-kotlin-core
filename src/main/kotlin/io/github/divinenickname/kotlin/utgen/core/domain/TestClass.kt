package io.github.divinenickname.kotlin.utgen.core.domain

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.ImplementBlock
import org.junit.jupiter.api.Test

class TestClass(private val originalClass: OriginalClass) : Class {

    override fun packageName(): String = originalClass.packageName()
    override fun simpleName(): String = originalClass.simpleName().plus("Test")
    override fun publicMethods(): Set<Method> = originalClass.publicMethods()

    private val objProperty = PropertySpec.builder("obj", originalClass.toClassName(), KModifier.PRIVATE)
        .initializer("${originalClass.simpleName()}()")
        .build()

    private val funSpecs = publicMethods()
        .map { it.name }
        .map {
        FunSpec.builder("${it}_goldencase")
            .addAnnotation(Test::class)
            .addCode(ImplementBlock.codeBlock())
            .addCode(CodeBlock.of("val actual = ${objProperty.name}.${it}()"))
            .build()
    }


    fun toTypeSpec() = TypeSpec
        .classBuilder(simpleName())
        .addModifiers(KModifier.INTERNAL)
        .addProperty(objProperty)
        .addFunctions(funSpecs)
        .build()
}

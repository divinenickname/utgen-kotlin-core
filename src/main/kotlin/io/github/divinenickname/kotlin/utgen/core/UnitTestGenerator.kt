package io.github.divinenickname.kotlin.utgen.core

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import org.junit.jupiter.api.Test

class UnitTestGenerator(
    private val className: ClassName,
    private val methodNames: Set<String>,
) {

    fun generate(): FileSpec {
        val objProperty = PropertySpec.builder("obj", className, KModifier.PRIVATE)
            .initializer("${className.simpleName}()")
            .build()

        val funSpecs = methodNames
            .map {
                FunSpec.builder("${it}_goldencase")
                    .addAnnotation(Test::class)
                    .addCode(CodeBlock.of("TODO(\"Implement\")\n"))
                    .addCode(CodeBlock.of("val actual = ${objProperty.name}.${it}()"))
                    .build()
            }

        val cls = TypeSpec
            .classBuilder("${className.simpleName}Test")
            .addModifiers(KModifier.INTERNAL)
            .addProperty(objProperty)
            .addFunctions(funSpecs)
            .build()

        return FileSpec.builder(className.packageName, "${className.simpleName}Test")
            .addType(cls)
            .build()
    }
}

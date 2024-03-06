package io.github.divinenickname.kotlin.utgen.core

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.domain.OriginalClass
import io.github.divinenickname.kotlin.utgen.core.domain.OutputFile
import io.github.divinenickname.kotlin.utgen.core.domain.TestClass
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Path

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

    /**
     * Generate unit-test and save it to test path.
     */
    fun generate(path: Path): FileSpec {
        val file = File(path.toUri())
        val lexer = file.readText().let(CharStreams::fromString).let(::KotlinLexer)
        val parser = KotlinParser(CommonTokenStream(lexer))
        val ctx = parser.kotlinFile()

        val testClass = OriginalClass(ctx).let(::TestClass)

        return FileSpec.builder(testClass.packageName(), testClass.simpleName())
            .addType(testClass.toTypeSpec())
            .build()
    }

    fun generateAndSave(path: Path) {
        val outputFile = OutputFile(path.toString())
        generate(path).writeTo(outputFile)
    }
}



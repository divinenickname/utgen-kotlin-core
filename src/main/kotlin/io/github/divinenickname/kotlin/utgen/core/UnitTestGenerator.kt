package io.github.divinenickname.kotlin.utgen.core

import com.squareup.kotlinpoet.FileSpec
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.domain.OriginalClass
import io.github.divinenickname.kotlin.utgen.core.domain.OutputFile
import io.github.divinenickname.kotlin.utgen.core.domain.TestClass
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.nio.file.Path

/**
 * Generate whole unit-test class.
 */
class UnitTestGenerator {

    /**
     * Generate unit-test without saving. You should to save it manually.
     *
     * @param path Kotlin source file
     */
    @Deprecated("This method is not supported multiple classes, use generateAll")
    fun generate(path: Path): FileSpec {
        val ctx = parse(path)

        val testClass = OriginalClass(ctx).let(::TestClass)

        return FileSpec.builder(testClass.packageName(), testClass.simpleName())
            .addType(testClass.toTypeSpec())
            .addImport("org.junit.jupiter.api", "Assertions")
            .build()
    }

    /**
     * Generate test classes for all classes in .kt file.
     */
    fun generateAll(path: Path): List<FileSpec> {
        val ctx = parse(path)
        val fileSpecs = mutableListOf<FileSpec>()

        for (idx in 0..<ctx.topLevelObject().size) {
            OriginalClass(ctx, idx).let(::TestClass).let { testClass ->
                FileSpec.builder(testClass.packageName(), testClass.simpleName())
                    .addType(testClass.toTypeSpec())
                    .addImport("org.junit.jupiter.api", "Assertions")
                    .also { builder -> testClass.imports().forEach { builder.addImport(it, "") } }
                    .build()
            }.also(fileSpecs::add)
        }

        return fileSpecs
    }

    private fun parse(path: Path): KotlinParser.KotlinFileContext {
        val file = File(path.toUri())
        val lexer = file.readText().let(CharStreams::fromString).let(::KotlinLexer)
        val parser = KotlinParser(CommonTokenStream(lexer))

        return parser.kotlinFile()
    }

    /**
     * Generate unit-tests and save it to test path using package provided from Kotlin .kt file.
     * 'main' replaces by 'test'
     *
     * @param path Kotlin source file
     */
    @Deprecated("Useless method, should be deleted in 1.4.0")
    fun generateAndSave(path: Path) {
        val outputFile = OutputFile(path.toString())
        generate(path).writeTo(outputFile)
    }
}



package io.github.divinenickname.kotlin.utgen.core

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.Import
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.domain.OriginalClass
import io.github.divinenickname.kotlin.utgen.core.domain.OutputFile
import io.github.divinenickname.kotlin.utgen.core.domain.TestClass
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Assertions
import java.io.File
import java.nio.file.Path

class UnitTestGenerator {

    /**
     * Generate unit-test without saving. You should to save it manually.
     *
     * @param path Kotlin source file
     */
    fun generate(path: Path): FileSpec {
        val file = File(path.toUri())
        val lexer = file.readText().let(CharStreams::fromString).let(::KotlinLexer)
        val parser = KotlinParser(CommonTokenStream(lexer))
        val ctx = parser.kotlinFile()

        val testClass = OriginalClass(ctx).let(::TestClass)

        return FileSpec.builder(testClass.packageName(), testClass.simpleName())
            .addType(testClass.toTypeSpec())
            .addImport("org.junit.jupiter.api", "Assertions")
            .build()
    }

    /**
     * Generate unit-tests and save it to test path using package provided from Kotlin .kt file.
     * 'main' replaces by 'test'
     *
     * @param path Kotlin source file
     */
    fun generateAndSave(path: Path) {
        val outputFile = OutputFile(path.toString())
        generate(path).writeTo(outputFile)
    }
}



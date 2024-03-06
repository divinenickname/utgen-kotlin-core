package io.github.divinenickname.kotlin.utgen.core

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.kotest.matchers.collections.shouldContainAll
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.jupiter.api.Test
import java.io.File

class MethodExtractorTest {
    private val input = File("src/main/kotlin/io/github/divinenickname/kotlin/utgen/core/TestClass.kt")
        .readText()

    @Test
    fun checkMethods() {
        val lexer = KotlinLexer(CharStreams.fromString(input))
        val parser = KotlinParser(CommonTokenStream(lexer))

        val kotlinFileContext = parser.kotlinFile()
        val methodExtractor = MethodExtractor()

        ParseTreeWalker().walk(methodExtractor, kotlinFileContext)

        methodExtractor.methods shouldContainAll setOf("voidMethod", "nonVoidMethod")
    }
}

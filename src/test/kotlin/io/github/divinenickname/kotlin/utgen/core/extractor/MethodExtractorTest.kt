package io.github.divinenickname.kotlin.utgen.core.extractor

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.collections.shouldContainAll
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.junit.jupiter.api.Test
import java.io.File

class MethodExtractorTest {
    private val input = File("src/test/resources/TestClass.kt")
        .readText()

    @Test
    fun checkMethods() {
        val expected = setOf(
            Method("voidMethod"),
            Method("publicScopeMethod"),
            Method("nonVoidMethod", "String")
        )

        val lexer = KotlinLexer(CharStreams.fromString(input))
        val parser = KotlinParser(CommonTokenStream(lexer))

        val kotlinFileContext = parser.kotlinFile()
        val methodExtractor = MethodExtractor()

        ParseTreeWalker().walk(methodExtractor, kotlinFileContext)

        methodExtractor.methods shouldContainAll expected
    }
}

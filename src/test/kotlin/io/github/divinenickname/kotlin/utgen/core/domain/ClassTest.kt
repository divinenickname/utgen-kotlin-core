package io.github.divinenickname.kotlin.utgen.core.domain

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.kotest.matchers.shouldBe
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Test
import java.io.File

class ClassTest {

    private val file =  File("src/test/kotlin/io/github/divinenickname/kotlin/utgen/core/TestClass.kt")
    private val lexer = file.readText().let(CharStreams::fromString).let(::KotlinLexer)
    private val parser = KotlinParser(CommonTokenStream(lexer))
    private val ctx = parser.kotlinFile()

    private val given = Class(ctx)

    @Test
    fun classNameTest() {
        given.className shouldBe "TestClass"
    }

    @Test
    fun packageNameTest() {
        given.packageName shouldBe "io.github.divinenickname.kotlin.utgen.core"
    }

    @Test
    fun methodsNameTest() {
        given.methods.map { it.name }.toSet() shouldBe setOf("voidMethod", "nonVoidMethod", "publicScopeMethod")
    }
}

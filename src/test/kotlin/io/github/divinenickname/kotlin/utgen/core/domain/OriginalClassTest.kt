package io.github.divinenickname.kotlin.utgen.core.domain

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.kotest.matchers.maps.shouldContainAll
import io.kotest.matchers.shouldBe
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Test
import java.io.File

class OriginalClassTest {
    private val expectedSimpleName = "TestClass"
    private val expectedPackageName = "io.github.divinenickname.kotlin.utgen.core"
    private val expectedPublicMethods = setOf("voidMethod", "nonVoidMethod", "publicScopeMethod").map(::Method).toSet()

    private val file =  File("src/main/kotlin/io/github/divinenickname/kotlin/utgen/core/TestClass.kt")
    private val lexer = file.readText().let(CharStreams::fromString).let(::KotlinLexer)
    private val parser = KotlinParser(CommonTokenStream(lexer))
    private val ctx = parser.kotlinFile()

    private val given = OriginalClass(ctx)

    @Test
    fun primaryConstructor_goldencase() {
        given.primaryConstructor().fields shouldContainAll mapOf("str" to "String")
    }

    @Test
    fun classNameTest() {
        given.simpleName() shouldBe expectedSimpleName
    }

    @Test
    fun packageNameTest() {
        given.packageName() shouldBe expectedPackageName
    }

    @Test
    fun methodsNameTest() {
        given.publicMethods().map { it.name } shouldBe expectedPublicMethods.map { it.name }
    }
}

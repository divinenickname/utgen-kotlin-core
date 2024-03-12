package io.github.divinenickname.kotlin.utgen.core.domain

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.kotest.matchers.shouldBe
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Test
import java.io.File

abstract class AbstractClassTest(
    private val expectedSimpleName: String,
    private val expectedPackageName: String,
    private val expectedPublicMethods: Set<Method>
) {
    private val file =  File("src/main/kotlin/io/github/divinenickname/kotlin/utgen/core/TestClass.kt")
    private val lexer = file.readText().let(CharStreams::fromString).let(::KotlinLexer)
    private val parser = KotlinParser(CommonTokenStream(lexer))
    val ctx = parser.kotlinFile()

    abstract val given: TestingClass

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

package io.github.divinenickname.kotlin.utgen.core.domain

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinLexer
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.jupiter.api.Test
import java.io.File

internal class OriginalClassTest {

    private fun getOriginalClassFromSource(filePath: String) = File(filePath).readText()
        .let(CharStreams::fromString)
        .let(::KotlinLexer)
        .let(::CommonTokenStream)
        .let(::KotlinParser)
        .kotlinFile()
        .let(::OriginalClass)

    @Test
    fun classNameTest() {
        val originalClass = getOriginalClassFromSource("src/test/resources/TestClass.kt")

        val actual = originalClass.simpleName()
        val expected = "TestClass"

        actual shouldBe expected
    }

    @Test
    fun packageNameTest() {
        val originalClass = getOriginalClassFromSource("src/test/resources/TestClass.kt")

        val actual = originalClass.packageName()
        val expected = "io.github.divinenickname.kotlin.utgen.core"

        actual shouldBe expected
    }

    @Test
    fun methodsNameTest() {
        val originalClass = getOriginalClassFromSource("src/test/resources/TestClass.kt")

        val actual = originalClass.publicMethods()
        val expected = setOf(
            Method("voidMethod", ReturnValue("Unit")),
            Method("nonVoidMethod", ReturnValue("String")),
            Method("publicScopeMethod", ReturnValue("Unit")),
        )

        actual shouldBe expected
    }

    @Test
    fun importsTest() {
        val originalClass = getOriginalClassFromSource("src/test/resources/ClassWithExternalImports.txt")

        val actual = originalClass.imports()
        val expected = listOf("org.example.MyClass")

        actual shouldContainAll expected
    }

    @Test
    fun importsEmpty_Test() {
        val originalClass = getOriginalClassFromSource("src/test/resources/ClassWithoutExternalImports.txt")

        val actual = originalClass.imports()
        val expected = emptySet<String>()

        actual shouldContainAll expected
    }

    @Test
    fun publicMethods_correctImports() {
        val originalClass = getOriginalClassFromSource("src/test/resources/ClassWithExternalImports.txt")

        val actual = originalClass.publicMethods()
        val expected = setOf(
            Method("method", ReturnValue("org.example.MyClass", "MyClass"))
        )

        actual shouldContainAll expected
    }
}

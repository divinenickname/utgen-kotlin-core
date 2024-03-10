package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class UnitAssertionBlockTest {

    @Test
    fun codeBlock_void() {
        val propSpec = PropertySpec.builder("MyObj", String::class).build()
        val expected = CodeBlock
            .of("Assertions.assertDoesNotThrow { ${propSpec.name}.voidMethod() }")

        val actual = UnitAssertionBlock(propSpec, Method("voidMethod")).codeBlock()

        actual shouldBe expected
    }

    @Test
    fun codeBlock_nonVoid() {
        val propSpec = PropertySpec.builder("MyObj", String::class).build()
        val expected = CodeBlock
            .of("val actual = ${propSpec.name}.nonVoidMethod()")

        val actual = UnitAssertionBlock(propSpec, Method("nonVoidMethod", "String")).codeBlock()

        actual shouldBe expected
    }

}

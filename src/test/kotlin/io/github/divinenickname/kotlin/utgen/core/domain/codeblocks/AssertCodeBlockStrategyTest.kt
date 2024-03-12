package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AssertCodeBlockStrategyTest {
    private val propSpec = PropertySpec
        .builder("abc", ClassName("org.example", "myClass")).build()

    @Test
    fun returnValueIsUnit() {
        val actual = AssertCodeBlockStrategy(propSpec, Method("test")).codeBlock()
        val expected = CodeBlock.of("Assertions.assertDoesNotThrow { abc.test() }")

        actual shouldBe expected
    }

    @Test
    fun returnValueIsObject() {
        val actual = AssertCodeBlockStrategy(propSpec, Method("test", "String")).codeBlock()
        val expected = CodeBlock.of("val actual = abc.test()")

        actual shouldBe expected
    }
}

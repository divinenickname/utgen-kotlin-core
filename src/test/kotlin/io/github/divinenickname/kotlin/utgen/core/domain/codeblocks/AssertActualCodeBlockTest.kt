package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AssertActualCodeBlockTest {

    private val propSpec = PropertySpec
        .builder("abc", ClassName("org.example", "myClass")).build()

    @Test
    fun codeBlock_goldencase() {
        val actual = AssertActualCodeBlock(propSpec, Method("test")).codeBlock()
        val expected = CodeBlock.of("val actual = abc.test()")

        actual shouldBe expected
    }
}

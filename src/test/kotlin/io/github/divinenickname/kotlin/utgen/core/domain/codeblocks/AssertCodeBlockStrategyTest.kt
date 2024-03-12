package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AssertCodeBlockStrategyTest {
    private val propSpec = ObjectProperty("org.example", "myClass").toPropertySpec()

    @Test
    fun returnValueIsUnit() {
        val actual = AssertCodeBlockStrategy(propSpec, Method("test")).codeBlock()
        val expected = CodeBlock.of("Assertions.assertDoesNotThrow { obj.test() }")

        actual shouldBe expected
    }

    @Test
    fun returnValueIsObject() {
        val actual = AssertCodeBlockStrategy(propSpec, Method("test", "String")).codeBlock()
        val expected = """
            val expected = String()
            val actual = obj.test()

            Assertions.assertEquals(expected, actual)
        """.trimIndent().let(CodeBlock::of)

        actual shouldBe expected
    }
}

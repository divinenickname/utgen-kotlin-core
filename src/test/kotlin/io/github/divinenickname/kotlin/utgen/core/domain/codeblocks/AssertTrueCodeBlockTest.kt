package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AssertTrueCodeBlockTest {
    private val propSpec = ObjectProperty("org.example", "myClass").toPropertySpec()

    @Test
    fun codeBlock() {
        val actual = AssertTrueCodeBlock(propSpec, Method("test", "Boolean")).codeBlock()
        val expected = """
            val actual = obj.test()

            Assertions.assertTrue(actual)
        """.trimIndent()
            .let(CodeBlock::of)

        actual shouldBe expected
    }
}

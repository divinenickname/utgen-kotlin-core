package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class AssertPrimitiveMethodCodeBlockTest {
    private val propSpec = ObjectProperty("org.example", "myClass").toPropertySpec()

    @Test
    fun codeBlock_goldencase() {
        val actual = AssertPrimitiveMethodCodeBlock(propSpec, Method("test", "Boolean")).codeBlock()
        val expected = """
      val expected:Boolean = TODO('Add value here')
      val actual = obj.test()

      Assertions.assertEquals(expected, actual)
    """.trimIndent().let(CodeBlock::of)

        actual shouldBe expected
    }
}

package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DefaultAssertionCodeBlockTest {

    private val propSpec = ObjectProperty("org.example", "myClass").toPropertySpec()

    @Test
    fun codeBlock_goldencase() {
        val actual = DefaultAssertionCodeBlock(propSpec, Method("test", "MyRetObject")).codeBlock()
        val expected = """
            val expected = MyRetObject()
            val actual = obj.test()
            
            Assertions.assertEquals(expected, actual)
        """.trimIndent()
            .let(CodeBlock::of)

        actual shouldBe expected
    }

    @Test
    fun codeBlock_nullable() {
        val actual = DefaultAssertionCodeBlock(propSpec, Method("test", "MyRetObject?")).codeBlock()
        val expected = """
            val expected = MyRetObject()
            val actual = obj.test()
            
            Assertions.assertEquals(expected, actual)
        """.trimIndent()
            .let(CodeBlock::of)

        actual shouldBe expected
    }
}

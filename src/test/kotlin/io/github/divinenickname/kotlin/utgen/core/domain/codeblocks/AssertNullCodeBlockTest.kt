package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class AssertNullCodeBlockTest {
    private val propSpec = ObjectProperty("org.example", "myClass").toPropertySpec()

//    @Test
//    fun codeBlock() {
//        val actual = AssertNullCodeBlock(propSpec, Method("test", "String?")).codeBlock()
//
//        val expected = CodeBlock.of("Assertions.assertNull( obj.test() )")
//
//        actual shouldBe expected
//    }
}

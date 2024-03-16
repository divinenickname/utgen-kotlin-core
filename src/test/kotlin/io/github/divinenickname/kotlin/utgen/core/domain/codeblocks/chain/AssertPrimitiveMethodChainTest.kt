package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class AssertPrimitiveMethodChainTest {

    @Test
    fun isValid_shouldTrue() {
        val actual = AssertPrimitiveMethodChain(mock(), Method("testName", "Short")).isValid()

        actual shouldBe true
    }

    @Test
    fun isValid_shouldFalse() {
        val actual = AssertPrimitiveMethodChain(mock(), Method("test", "OtherClass")).isValid()

        actual shouldBe false
    }

    @Test
    fun testMethodName() {
        val actual = AssertPrimitiveMethodChain(mock(), Method("testName", "String")).testMethodName()

        actual shouldBe "testNameTest"
    }
}

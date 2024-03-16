package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AssertTrueChainTest {
    @Test
    fun isValid_shouldTrue() {
        val actual = AssertTrueChain(Mockito.mock(), Method("test", "Boolean")).isValid()

        actual shouldBe true
    }

    @Test
    fun isValid_shouldFalse() {
        val actual = AssertTrueChain(Mockito.mock(), Method("test", "String")).isValid()

        actual shouldBe false
    }

    @Test
    fun testMethodName() {
        val actual = AssertTrueChain(Mockito.mock(), Method("testName", "Boolean")).testMethodName()

        actual shouldBe "testName_isTrue"
    }
}

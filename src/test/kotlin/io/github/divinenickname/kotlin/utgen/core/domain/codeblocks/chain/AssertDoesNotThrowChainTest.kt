package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class AssertDoesNotThrowChainTest {

    @Test
    fun isValid_shouldTrue() {
        val actual = AssertDoesNotThrowChain(mock(), Method("test", "Unit")).isValid()

        actual shouldBe true
    }

    @Test
    fun isValid_shouldFalse() {
        val actual = AssertDoesNotThrowChain(mock(), Method("test", "String")).isValid()

        actual shouldBe false
    }

    @Test
    fun testMethodName() {
        val actual = AssertDoesNotThrowChain(mock(), Method("testName", "String")).testMethodName()

        actual shouldBe "testName_notThrowTest"
    }
}

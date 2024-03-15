package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AssertDoesNotThrowChainTest {

    @Test
    fun isValid_shouldTrue() {
        val actual = AssertDoesNotThrowChain().isValid(Method("test", "Unit"))

        actual shouldBe true
    }

    @Test
    fun isValid_shouldFalse() {
        val actual = AssertDoesNotThrowChain().isValid(Method("test", "String"))

        actual shouldBe false
    }

    @Test
    fun testMethodName() {
        val actual = AssertDoesNotThrowChain().testMethodName(Method("testName", "String"))

        actual shouldBe "testName_notThrowTest"
    }
}

package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AssertNullChainTest {

    @Test
    fun isValid_shouldTrue() {
        val actual = AssertNullChain().isValid(Method("test", "String?"))

        actual shouldBe true
    }

    @Test
    fun isValid_shouldFalse() {
        val actual = AssertNullChain().isValid(Method("test", "String"))

        actual shouldBe false
    }

    @Test
    fun testMethodName() {
        val actual = AssertNullChain().testMethodName(Method("testName", "String"))

        actual shouldBe "testName_isNullTest"
    }
}

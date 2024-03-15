package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AssertPrimitiveMethodChainTest {

    @Test
    fun isValid_shouldTrue() {
        val actual = AssertPrimitiveMethodChain().isValid(Method("test", "Short"))

        actual shouldBe true
    }

    @Test
    fun isValid_shouldFalse() {
        val actual = AssertPrimitiveMethodChain().isValid(Method("test", "OtherClass"))

        actual shouldBe false
    }

    @Test
    fun testMethodName() {
        val actual = AssertPrimitiveMethodChain().testMethodName(Method("testName", "String"))

        actual shouldBe "testNameTest"
    }
}

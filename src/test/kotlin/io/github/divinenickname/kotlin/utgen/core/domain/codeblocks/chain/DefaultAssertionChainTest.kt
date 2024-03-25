package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks.chain

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class DefaultAssertionChainTest {

    @Test
    fun isValid_shouldTrue() {
        val actual = DefaultAssertionChain(Mockito.mock(), Method("testName", "Obj")).isValid()

        actual shouldBe true
    }

    @Test
    fun isValid_nullableShouldTrue() {
        val actual = DefaultAssertionChain(Mockito.mock(), Method("testName", "Obj?")).isValid()

        actual shouldBe true
    }

    @Test
    fun isValid_primitiveNullableShouldFalse() {
        val actual = DefaultAssertionChain(Mockito.mock(), Method("testName", "Boolean?")).isValid()

        actual shouldBe false
    }

    @Test
    fun isValid_unitShouldFalse() {
        val actual = DefaultAssertionChain(Mockito.mock(), Method("testName", "Unit")).isValid()

        actual shouldBe false
    }

    @Test
    fun isValid_booleanShouldFalse() {
        val actual = DefaultAssertionChain(Mockito.mock(), Method("testName", "Boolean")).isValid()

        actual shouldBe false
    }

    @Test
    fun isValid_primitivesShouldFalse() {
        val actual = DefaultAssertionChain(Mockito.mock(), Method("testName", "Int")).isValid()

        actual shouldBe false
    }
}

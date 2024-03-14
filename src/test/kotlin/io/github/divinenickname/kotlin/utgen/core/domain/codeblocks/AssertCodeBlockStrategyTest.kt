package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.reflect.KClass

class AssertCodeBlockStrategyTest {
    private val propSpec = ObjectProperty("org.example", "myClass").toPropertySpec()

    @Test
    fun returnValueIsUnit() {
        val actual = AssertCodeBlockStrategy(propSpec, Method("test"))
            .getStrategy().javaClass.kotlin
        val expected = AssertDoesNotThrowCodeBlock::class

        actual shouldBe expected
    }

    @Test
    fun returnValueIsObject() {
        val actual = AssertCodeBlockStrategy(propSpec, Method("test", "String"))
            .getStrategy().javaClass.kotlin
        val expected = AssertActualCodeBlock::class

        actual shouldBe expected
    }

    @ParameterizedTest
    @ValueSource(strings = ["Byte", "Short", "Int", "Long", "Float", "Double", "Char", "Boolean"])
    fun returnValueIsPrimitive(primitive: String) {
        val actual = AssertCodeBlockStrategy(propSpec, Method("test", primitive))
            .getStrategy().javaClass.kotlin
        val expected = AssertPrimitiveMethodCodeBlock::class

        actual shouldBe expected
    }
}

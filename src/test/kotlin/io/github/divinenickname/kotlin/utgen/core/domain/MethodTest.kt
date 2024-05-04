package io.github.divinenickname.kotlin.utgen.core.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MethodTest {

    @ParameterizedTest
    @ValueSource(strings = ["String?", "String? "])
    fun isNullable_true(returnType: String) {
        val obj = Method("abc", ReturnValue(className = returnType))

        obj.returnValue.isNullable() shouldBe true
    }

    @Test
    fun isNullable_false() {
        val obj = Method("abc", ReturnValue(className = "String"))

        obj.returnValue.isNullable() shouldBe false
    }

    @Test
    fun returnValue_nullable() {
        val obj = Method("abc", ReturnValue(className = "String?"))

        obj.returnValue.className() shouldBe "String"
    }
}

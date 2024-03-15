package io.github.divinenickname.kotlin.utgen.core.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MethodTest {

    @Test
    fun isNullable_true() {
        val obj = Method("abc", "String?")

        obj.isNullable() shouldBe true
    }

    @Test
    fun isNullable_false() {
        val obj = Method("abc", "String")

        obj.isNullable() shouldBe false
    }

    @Test
    fun returnValue_nullable() {
        val obj = Method("abc", "String?")

        obj.returnValue() shouldBe "String"
    }
}

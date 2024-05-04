package io.github.divinenickname.kotlin.utgen.core.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ReturnValueTest {
    @Test
    fun isNullable_isTrue() {
        val obj = ReturnValue("Any?")

        val actual = obj.isNullable()

        Assertions.assertTrue(actual)
    }

    @Test
    fun isNullable_isFalse() {
        val obj = ReturnValue("Any")

        val actual = obj.isNullable()

        Assertions.assertFalse(actual)
    }

    @Test
    fun classNameTest() {
        val obj = ReturnValue("org.example", "MyClass")

        val expected = "MyClass"
        val actual = obj.className()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun packageNameTest() {
        val obj = ReturnValue("org.example", "MyClass")

        val expected = "org.example"
        val actual = obj.packageName()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun toString_withPackage() {
        val obj = ReturnValue("org.example", "MyClass")

        val expected = "org.example.MyClass"
        val actual = obj.toString()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun toString_withoutPackage() {
        val obj = ReturnValue("MyClass")

        val expected = "MyClass"
        val actual = obj.toString()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun equals_isTrue() {
        val obj = ReturnValue("org.example", "MyClass")

        val actual = obj == ReturnValue("org.example", "MyClass")

        Assertions.assertTrue(actual)
    }

    @Test
    fun equals_isFalse() {
        val obj = ReturnValue("org.example", "MyClass")

        val actual = obj == ReturnValue("org.example.yep", "MyClass")

        Assertions.assertFalse(actual)
    }
}

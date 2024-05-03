package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class TestCaseProcessorTest {
    private val objProperty = ObjectProperty("org.example", "MyClass")

    @Test
    fun generateCodeBlocksTest() {
        val obj = TestCaseProcessor(objProperty, Method("methodName", "Any"))

        val actual = obj.generateCodeBlocks()

        Assertions.assertTrue(actual.isNotEmpty())
    }
}

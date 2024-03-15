package io.github.divinenickname.kotlin.utgen.core.domain.testclass

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PublicMethodSpecTest {

    private val propSpec = PropertySpec
        .builder("abc", ClassName("org.example", "myClass")).build()
    private val method = Method("test")
    private val methodSpec = PublicMethodSpec(method, propSpec)

    @Test
    fun methodName_correct() {
        methodSpec.toSpec().name shouldBe "test_goldencase"
    }

    @Test
    fun annotationTest_correct() {
        methodSpec.toSpec().annotations shouldBe listOf(AnnotationSpec.builder(Test::class).build())
    }

    @Test
    fun codeBlock_correct() {
        val expected = """
            TODO("Implement")
            Assertions.assertDoesNotThrow { abc.test() }
        """.trimIndent()

        methodSpec.toSpec().body shouldBe CodeBlock.of(expected)
    }
}

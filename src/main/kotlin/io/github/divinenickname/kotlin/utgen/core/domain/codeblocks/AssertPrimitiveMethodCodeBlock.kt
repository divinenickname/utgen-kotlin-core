package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method

class AssertPrimitiveMethodCodeBlock(private val objProperty: PropertySpec, private val method: Method): CodeBlockObj {
    override fun codeBlock(): CodeBlock {
        val blockStr = """
            val expected:${method.returnValue()} = TODO('Add value here')
            val actual = ${objProperty.name}.${method.name}()
            
            Assertions.assertEquals(expected, actual)
        """.trimIndent()

        return blockStr.let(CodeBlock::of)
    }
}

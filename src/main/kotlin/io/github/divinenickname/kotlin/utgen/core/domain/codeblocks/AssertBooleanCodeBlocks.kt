package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.PropertySpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method

class AssertFalseCodeBlock(
    private val objProperty: PropertySpec,
    private val method: Method
): CodeBlockObj {
    override fun codeBlock(): CodeBlock {
        val blockStr = """
            val actual = ${objProperty.name}.${method.name}()
    
            Assertions.assertFalse(actual)
        """.trimIndent()
        return blockStr.let(CodeBlock::of)
    }
}

class AssertTrueCodeBlock(
    private val objProperty: PropertySpec,
    private val method: Method
): CodeBlockObj {
    override fun codeBlock(): CodeBlock {
        val blockStr = """
            val actual = ${objProperty.name}.${method.name}()
    
            Assertions.assertTrue(actual)
        """.trimIndent()
        return blockStr.let(CodeBlock::of)
    }
}

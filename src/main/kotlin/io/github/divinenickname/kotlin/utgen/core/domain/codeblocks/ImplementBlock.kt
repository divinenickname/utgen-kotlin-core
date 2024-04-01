package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock

/**
 * Implement codeBlock. Contains simple 'todo' impl block
 */
object ImplementBlock: CodeBlockObj {
    override fun codeBlock() = CodeBlock.of("TODO(\"Implement\")\n")
}

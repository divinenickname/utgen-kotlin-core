package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock

object ImplementBlock: CodeBlockObj {
    override fun codeBlock() = CodeBlock.of("TODO(\"Implement\")\n")
}

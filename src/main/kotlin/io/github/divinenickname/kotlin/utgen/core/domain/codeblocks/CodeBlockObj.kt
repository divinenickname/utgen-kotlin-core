package io.github.divinenickname.kotlin.utgen.core.domain.codeblocks

import com.squareup.kotlinpoet.CodeBlock

/**
 * Common interface for all codeBlocks
 */
interface CodeBlockObj {

    /**
     * Generate body for test method.
     *
     * @return body kotlinPoet codeBlock
     */
    fun codeBlock(): CodeBlock
}

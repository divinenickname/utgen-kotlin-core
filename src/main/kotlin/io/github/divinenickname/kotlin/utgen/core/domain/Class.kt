package io.github.divinenickname.kotlin.utgen.core.domain

import KotlinParser
import org.antlr.v4.runtime.tree.ParseTreeWalker
import io.github.divinenickname.kotlin.utgen.core.MethodExtractor

class Class(
    private val ctx: KotlinParser.KotlinFileContext
) {
    private val methodExtractor = MethodExtractor().also {
        ParseTreeWalker().walk(it, ctx)
    }

    val className: String = ctx.topLevelObject().first().declaration().classDeclaration().simpleIdentifier().text
    val packageName: String = ctx.packageHeader().identifier().simpleIdentifier().joinToString(".") { it.text }
    val methods: Set<Method> = methodExtractor.methods.map(::Method).toSet()
}

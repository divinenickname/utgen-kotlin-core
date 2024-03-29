package io.github.divinenickname.kotlin.utgen.core.domain

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.extractor.MethodExtractor
import org.antlr.v4.runtime.tree.ParseTreeWalker

class OriginalClass(
    private val ctx: KotlinParser.KotlinFileContext
): Class {
    private val methodExtractor = MethodExtractor().also {
        ParseTreeWalker().walk(it, ctx)
    }

    override fun packageName(): String = ctx.packageHeader().identifier().simpleIdentifier()
        .joinToString(".") { it.text }

    override fun simpleName(): String = ctx.topLevelObject().first()
        .declaration().classDeclaration().simpleIdentifier().text

    override fun publicMethods(): Set<Method> = methodExtractor.methods
}

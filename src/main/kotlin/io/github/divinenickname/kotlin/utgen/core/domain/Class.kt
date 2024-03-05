package io.github.divinenickname.kotlin.utgen.core.domain

import io.github.divinenickname.kotlin.utgen.core.MethodExtractor
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import org.antlr.v4.runtime.tree.ParseTreeWalker

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

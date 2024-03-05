package io.github.divinenickname.kotlin.utgen.core

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParserBaseListener

class MethodExtractor : KotlinParserBaseListener() {
    val methods = mutableListOf<String>()

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        ctx.takeIf { it.modifiers() == null || it.modifiers().text != "private"}
            ?.simpleIdentifier()?.text
            ?.let(methods::add)
    }
}

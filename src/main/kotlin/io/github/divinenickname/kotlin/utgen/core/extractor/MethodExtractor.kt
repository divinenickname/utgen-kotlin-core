package io.github.divinenickname.kotlin.utgen.core.extractor

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParserBaseListener
import io.github.divinenickname.kotlin.utgen.core.domain.Method

class MethodExtractor : KotlinParserBaseListener() {
    val methods = mutableSetOf<Method>()

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        ctx.takeIf { it.modifiers() == null || it.modifiers().text != "private"}
            ?.let {
                Method(name = ctx.simpleIdentifier().text, returnValue = ctx.type()?.text ?: "Unit")
                    .apply(methods::add)
            }
    }
}

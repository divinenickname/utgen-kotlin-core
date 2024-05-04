package io.github.divinenickname.kotlin.utgen.core.extractor

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParserBaseListener
import io.github.divinenickname.kotlin.utgen.core.domain.Method

/**
 * ANTLR parser extractor. Find and aggregate public methods
 */
class MethodExtractor : KotlinParserBaseListener() {
    val methods = mutableSetOf<Method>()

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        ctx.takeIf { it.modifiers() == null || it.modifiers().text != "private" }
            ?.let {
                val require = it.functionBody()?.block()?.statements()?.statement()
                    ?.mapNotNull { statement -> statement.requireCall()?.expression()?.text } ?: emptyList()

                Method(
                    name = ctx.simpleIdentifier().text,
                    returnValue = ctx.type()?.text ?: "Unit",
                    requireExpression = require)
                    .apply(methods::add)
            }
    }
}

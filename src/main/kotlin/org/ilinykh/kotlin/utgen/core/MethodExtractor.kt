package org.ilinykh.kotlin.utgen.core

import KotlinParserBaseListener

class MethodExtractor : KotlinParserBaseListener() {
    val methods = mutableListOf<String>()

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        ctx.takeIf { it.modifiers() == null || it.modifiers().text != "private"}
            ?.simpleIdentifier()?.text
            ?.let(methods::add)
    }
}

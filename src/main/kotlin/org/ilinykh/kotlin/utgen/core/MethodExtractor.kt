package org.ilinykh.kotlin.utgen.core

import KotlinParserBaseListener

class MethodExtractor : KotlinParserBaseListener() {
    val methods = mutableListOf<String>()

    override fun enterFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) {
        ctx.simpleIdentifier().text
            .let(methods::add)
    }
}

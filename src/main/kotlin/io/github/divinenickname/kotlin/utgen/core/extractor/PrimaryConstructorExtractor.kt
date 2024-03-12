package io.github.divinenickname.kotlin.utgen.core.extractor

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser.ClassParameterContext
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser.SimpleIdentifierContext
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser.TypeContext
import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParserBaseListener

class PrimaryConstructorExtractor : KotlinParserBaseListener() {
    val fields = mutableMapOf<String, String>()

    override fun enterPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) {
        val childCount = ctx.children.first().childCount
        for (childIndex in 0..childCount) {
            ctx.children.first().getChild(childIndex)
                .takeIf { it is ClassParameterContext }
                ?.takeIf { it.childCount > 0 && !it.text.contains("private") && it.text.contains("=") }
                ?.apply {
                    val fieldChildCount = this.childCount
                    val varSet = mutableSetOf<String>()
                    for (nestedChildIndex in 0..fieldChildCount) {
                        this.getChild(nestedChildIndex).takeIf { it is SimpleIdentifierContext || it is TypeContext }
                            ?.apply { varSet.add(this.text) }
                    }
                    fields[varSet.first()] = varSet.last()
                }
        }
    }
}

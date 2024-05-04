package io.github.divinenickname.kotlin.utgen.core.domain

import io.github.divinenickname.kotlin.utgen.core.antlr.KotlinParser
import io.github.divinenickname.kotlin.utgen.core.extractor.MethodExtractor
import org.antlr.v4.runtime.tree.ParseTreeWalker
import kotlin.jvm.Throws

/**
 * Convert parsed source code into domain structure.
 *
 * @param ctx antlr parser context
 * @param topLevelObjectIdx index of top level objects.
 * If your source file contains multiple classes you should to past correct object index.
 *
 * @throws [AssertionError] when topLevelObjectIdx is out of bound.
 */
class OriginalClass(
    private val ctx: KotlinParser.KotlinFileContext,
    topLevelObjectIdx: Int = 0,
) : Class {

    init {
        assert(topLevelObjectIdx < ctx.topLevelObject().size)
    }

    private val topLevelCtx = ctx.topLevelObject(topLevelObjectIdx)
    private val methodExtractor = MethodExtractor().also {
        ParseTreeWalker().walk(it, topLevelCtx)
    }

    override fun packageName(): String = ctx.packageHeader().identifier().simpleIdentifier()
        .joinToString(".") { it.text }

    override fun simpleName(): String = topLevelCtx
        .declaration().classDeclaration().simpleIdentifier().text

    override fun publicMethods(): Set<Method>{
        val imports = imports()
        val methods = methodExtractor.methods

        methods.map { method ->
            imports.firstOrNull { it.contains(method.returnValue.className()) }
                ?.let(method.returnValue::setPackageName)
        }

        return methods
    }

    override fun imports(): Set<String> = ctx.importList()?.importHeader()
        ?.map { it.identifier().text }
        ?.toSet() ?: emptySet()
}

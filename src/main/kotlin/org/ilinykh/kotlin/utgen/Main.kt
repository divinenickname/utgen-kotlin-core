package org.ilinykh.kotlin.utgen

import KotlinLexer
import KotlinParser
import com.squareup.kotlinpoet.ClassName
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.File
import kotlin.system.exitProcess

class Main

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        exitProcess(1)
    }

    val file = args.first().let(::File)
    val lexer = file.readText().let(CharStreams::fromString).let(::KotlinLexer)
    val parser = KotlinParser(CommonTokenStream(lexer))
    val methodExtractor = MethodExtractor()

    val ctx = parser.kotlinFile()
    ParseTreeWalker().walk(methodExtractor, ctx)

    val packageHeader = ctx.packageHeader().identifier().simpleIdentifier().joinToString(".") { it.text }
    val className = ctx.topLevelObject().first().declaration().classDeclaration().simpleIdentifier().text
    val methods = methodExtractor.methods.toSet()

    val output = file.path.replace("main", "test").let(::File).parent.replaceAfter("/test/kotlin/","")
    UnitTestGenerator(ClassName(packageHeader, className), methods).generate().writeTo(File(output))
}

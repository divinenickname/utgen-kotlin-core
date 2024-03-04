package org.ilinykh.kotlin.utgen

import KotlinLexer
import KotlinParser
import com.squareup.kotlinpoet.ClassName
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.ilinykh.kotlin.utgen.domain.Class
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
    val ctx = parser.kotlinFile()

    val clazz = Class(ctx)
    val output = file.path.replace("main", "test").let(::File).parent.replaceAfter("/test/kotlin/","")

    UnitTestGenerator(ClassName(clazz.packageName, clazz.className), clazz.methods.map { it.name }.toSet())
        .generate().writeTo(File(output))
}

package io.github.divinenickname.kotlin.utgen.core.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File

class OutputFileTest {
    private val given = File("src/main/kotlin/io/github/divinenickname/kotlin/utgen/core/TestClass.kt")
        .path.let(::OutputFile)

    @Test
    fun outputFilePathTest() {
        given.path shouldBe "src/test/kotlin"
    }
}

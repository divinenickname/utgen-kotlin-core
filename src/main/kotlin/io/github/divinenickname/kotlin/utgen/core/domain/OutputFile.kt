package io.github.divinenickname.kotlin.utgen.core.domain

import java.io.File

/**
 * Deprecated abstraction for saving test class
 */
@Deprecated("Relate to [UnitTestGenerator.generateAndSave]")
class OutputFile(originalFilePath: String): File(
    originalFilePath.replace("main", "test").replaceAfter("/test/kotlin/","")
)

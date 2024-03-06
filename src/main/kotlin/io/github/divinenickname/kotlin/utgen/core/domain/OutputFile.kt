package io.github.divinenickname.kotlin.utgen.core.domain

import java.io.File

class OutputFile(originalFilePath: String): File(
    originalFilePath.replace("main", "test").replaceAfter("/test/kotlin/","")
)

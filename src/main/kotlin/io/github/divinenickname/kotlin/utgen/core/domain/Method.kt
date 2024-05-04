package io.github.divinenickname.kotlin.utgen.core.domain

data class Method(
    val name: String,
    val returnValue: ReturnValue,
    private val requireExpressions: List<String> = emptyList()
) {
    fun requireExpressions() = requireExpressions
}

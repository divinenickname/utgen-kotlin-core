package io.github.divinenickname.kotlin.utgen.core.domain

class Method(
    val name: String,
    private val returnValue: String = "Unit",
    private val requireExpression: List<String> = emptyList()
) {

    fun isNullable(): Boolean = returnValue.trim().last() == '?'
    fun returnValue(): String = returnValue.takeUnless { isNullable() } ?: returnValue.trim().dropLast(1)
    fun requireExpressions(): List<String> = requireExpression

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Method

        if (name != other.name) return false
        return returnValue == other.returnValue
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + returnValue.hashCode()
        return result
    }
}

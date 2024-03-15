package io.github.divinenickname.kotlin.utgen.core.domain

class Method(
    val name: String,
    private val returnValue: String = "Unit"
) {

    fun isNullable(): Boolean = returnValue.last() == '?'
    fun returnValue(): String = returnValue.takeUnless { isNullable() } ?: returnValue.dropLast(1)

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

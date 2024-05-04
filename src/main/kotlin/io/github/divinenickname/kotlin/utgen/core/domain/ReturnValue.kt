package io.github.divinenickname.kotlin.utgen.core.domain

class ReturnValue(
    private val packageName: String = "",
    private var className: String,
) {
    private val isNullable: Boolean = className.trim().last() == '?'

    init {
        className = className.takeUnless { isNullable() } ?: className.trim().dropLast(1)
    }

    fun isNullable(): Boolean = isNullable
    fun className(): String = className
    fun packageName(): String = packageName

    override fun toString(): String = "$packageName.$className".takeIf { packageName.isNotEmpty() } ?: className
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReturnValue

        if (packageName != other.packageName) return false
        if (className != other.className) return false
        return isNullable == other.isNullable
    }

    override fun hashCode(): Int {
        var result = packageName.hashCode()
        result = 31 * result + className.hashCode()
        result = 31 * result + isNullable.hashCode()
        return result
    }


}

package io.github.divinenickname.kotlin.utgen.core

@Suppress
/**
 * Class only for testing parser and codegen.
 */
class TestClass(
    val str: String = "abc",
    val int: Int,
    private val privateInt: Int
) {

    fun voidMethod() {
        println("VOID method")
    }

    fun nonVoidMethod(): String {
        return "abcd"
    }

    public fun publicScopeMethod() {
        println()
    }

    private fun privateMethod(int: Long): String {
        return "private scope method"
    }
}

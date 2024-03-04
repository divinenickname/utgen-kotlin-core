package org.ilinykh.kotlin.utgen.core

/**
 * Class only for testing parser and codegen.
 */
class TestClass {

    fun voidMethod() {
        println("VOID method")
    }

    fun nonVoidMethod(): String {
        return "abcd"
    }

    public fun publicScopeMethod() {}

    private fun privateMethod(int: Long): String {
        return "private scope method"
    }
}

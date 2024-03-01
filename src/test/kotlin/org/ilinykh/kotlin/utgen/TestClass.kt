package org.ilinykh.kotlin.utgen

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
}

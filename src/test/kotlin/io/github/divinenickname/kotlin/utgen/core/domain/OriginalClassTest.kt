package io.github.divinenickname.kotlin.utgen.core.domain

class OriginalClassTest: AbstractClassTest(
    expectedSimpleName = "TestClass",
    expectedPackageName = "io.github.divinenickname.kotlin.utgen.core",
    expectedPublicMethods = setOf("voidMethod", "nonVoidMethod", "publicScopeMethod").map(::Method).toSet()
) {
    override val given = OriginalClass(ctx)
}

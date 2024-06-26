package io.github.divinenickname.kotlin.utgen.core.domain

class TestClassTest : AbstractClassTest(
    expectedSimpleName = "TestClassTest",
    expectedPackageName = "io.github.divinenickname.kotlin.utgen.core",
    expectedPublicMethods = setOf("voidMethod", "nonVoidMethod", "publicScopeMethod")
        .map { Method(it, ReturnValue("", "Any")) }
        .toSet()
) {
    override val given = TestClass(OriginalClass(ctx))
}

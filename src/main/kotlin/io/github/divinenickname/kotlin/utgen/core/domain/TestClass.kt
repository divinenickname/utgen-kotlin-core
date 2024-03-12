package io.github.divinenickname.kotlin.utgen.core.domain

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty
import io.github.divinenickname.kotlin.utgen.core.domain.testclass.PublicMethodSpec

class TestClass(
    private val packageName: String,
    private val originalClassName: String,
    private val className: String,
    private val publicMethods: Set<Method>,
) : TestingClass {
    constructor(originalClass: OriginalClass) : this(
        originalClassName = originalClass.simpleName(),
        packageName = originalClass.packageName(),
        className = "${originalClass.simpleName()}Test",
        publicMethods = originalClass.publicMethods()
    )

    private val objProperty = ObjectProperty(packageName, originalClassName).toPropertySpec()

    fun toTypeSpec() = TypeSpec
        .classBuilder(className)
        .addModifiers(KModifier.INTERNAL)
        .addProperty(objProperty)
        .addFunctions(publicMethods.map { PublicMethodSpec(it, objProperty).toSpec() })
        .build()

    override fun packageName(): String = packageName

    override fun simpleName(): String = className

    override fun publicMethods(): Set<Method> = publicMethods
}

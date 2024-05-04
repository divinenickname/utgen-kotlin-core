package io.github.divinenickname.kotlin.utgen.core.domain

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import io.github.divinenickname.kotlin.utgen.core.domain.testcase.TestCaseProcessor
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

/**
 * Used for generate whole test class with unit tests methods.
 *
 * @param packageName destination package name "org.example.domain".
 * @param originalClassName used for correct initialization test class object. e.g. "MyAwesomeClass".
 * @param className destination class name e.g. "MyAwesomeClassTest".
 * @param publicMethods Methods that should be covered by tests.
 */
class TestClass(
    private val packageName: String,
    private val originalClassName: String,
    private val className: String,
    private val publicMethods: Set<Method>,
) : Class {

    /**
     * Create test class from original class.
     */
    constructor(originalClass: OriginalClass) : this(
        originalClassName = originalClass.simpleName(),
        packageName = originalClass.packageName(),
        className = "${originalClass.simpleName()}Test",
        publicMethods = originalClass.publicMethods(),
    )

    private val objProperty = ObjectProperty(packageName, originalClassName)

    /**
     * Generate test class.
     */
    fun toTypeSpec(): TypeSpec {
        val funSpecs = publicMethods.map {
            TestCaseProcessor(objProperty, it).generateCodeBlocks()
        }.flatten().toSet()

        return TypeSpec
            .classBuilder(className)
            .addModifiers(KModifier.INTERNAL)
            .addFunctions(funSpecs)
            .build()
    }

    override fun packageName(): String = packageName

    override fun simpleName(): String = className

    override fun publicMethods(): Set<Method> = publicMethods

    override fun imports(): Set<String> = publicMethods()
        .filter { it.returnValue.packageName().isNotEmpty() }
        .map { it.returnValue.packageName() }.toSet()
}

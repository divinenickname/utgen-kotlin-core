package io.github.divinenickname.kotlin.utgen.core.domain.kpoet

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec

class ObjectProperty(
    private val packageName: String,
    private val className: String
) {

    private val simpleName = className.lowercase()

    fun toPropertySpec(): PropertySpec = PropertySpec
        .builder("obj", ClassName(packageName, simpleName), KModifier.PRIVATE)
        .initializer("${className}()")
        .build()
}

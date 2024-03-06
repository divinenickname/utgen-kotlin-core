package io.github.divinenickname.kotlin.utgen.core.domain

import com.squareup.kotlinpoet.ClassName

interface Class {

    fun packageName(): String
    fun simpleName(): String
    fun publicMethods(): Set<Method>
    fun toClassName(): ClassName = ClassName(packageName(), simpleName())
}

package io.github.divinenickname.kotlin.utgen.core.domain

interface Class {
    fun packageName(): String
    fun simpleName(): String
    fun publicMethods(): Set<Method>
    fun primaryConstructor(): Constructor
}

interface TestingClass {
    fun packageName(): String
    fun simpleName(): String
    fun publicMethods(): Set<Method>
}

package io.github.divinenickname.kotlin.utgen.core.domain

/**
 * Common interface for easy use class abstraction.
 */
interface Class {
    /**
     * Package name "org.example.domain".
     */
    fun packageName(): String

    /**
     * Class name "MyAwesomeClass"
     */
    fun simpleName(): String

    /**
     * All class's public methods.
     */
    fun publicMethods(): Set<Method>

    /**
     * Import declarations
     */
    fun imports(): Set<String>
}

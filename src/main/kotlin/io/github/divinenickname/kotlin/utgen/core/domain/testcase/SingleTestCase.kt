package io.github.divinenickname.kotlin.utgen.core.domain.testcase

import com.squareup.kotlinpoet.FunSpec
import io.github.divinenickname.kotlin.utgen.core.domain.Method
import io.github.divinenickname.kotlin.utgen.core.domain.kpoet.ObjectProperty

abstract class TestCase(
    private val objProperty: ObjectProperty,
    private val method: Method,
) {

    abstract fun canApply(): Boolean
    abstract fun funSpecs(): Set<FunSpec>

}

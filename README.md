[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
![main branch build](https://github.com/divinenickname/utgen-kotlin-core/actions/workflows/mainbranch-build.yml/badge.svg)

# Utgen-core
Simple unit-tests generator for Kotlin language.

## Features
- [x] Generate empty methods for golden cases
- [ ] Generate if-else statements cases
- [ ] Generate try-catch statements cases

## How to
Add dependency:
```kotlin
implementation("io.github.divinenickname.kotlin.utgen:utgen-core:1.1.1")
```

After that you can start generating test using two simple methods:
```kotlin
UnitTestGenerator()
    .generateAndSave("path/to/kotlin/Class.kt")
```

OR
```kotlin
UnitTestGenerator()
    .generate("path/to/kotlin/Class.kt")
    .writeTo(file or path)
```

#### Example
Your source class is:
```kotlin
package io.github.divinenickname.kotlin.utgen.core

class TestClass {

    fun voidMethod() {
        println("VOID method")
    }

    fun nonVoidMethod(): String {
        return "abcd"
    }

    public fun publicScopeMethod() {}

    private fun privateMethod(int: Long): String {
        return "private scope method"
    }
}
```

Library generates code:
```kotlin
package io.github.divinenickname.kotlin.utgen.core

import org.junit.jupiter.api.Test

internal class TestClassTest {
  private val obj: TestClassTest = TestClass()

  @Test
  public fun voidMethod_goldencase() {
    TODO("Implement")
    val actual = obj.voidMethod()
  }

  @Test
  public fun nonVoidMethod_goldencase() {
    TODO("Implement")
    val actual = obj.nonVoidMethod()
  }

  @Test
  public fun publicScopeMethod_goldencase() {
    TODO("Implement")
    val actual = obj.publicScopeMethod()
  }
}
```

## See also
- Idea IDE plugin: https://github.com/divinenickname/utgen-kotlin-idea-plugin

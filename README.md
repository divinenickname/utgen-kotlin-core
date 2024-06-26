[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
![main branch build](https://github.com/divinenickname/utgen-kotlin-core/actions/workflows/PR-and-main-build.yml/badge.svg)
![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https://repo1.maven.org/maven2/io/github/divinenickname/kotlin/utgen/utgen-core/maven-metadata.xml&style=flat&label=sonatype-central&color=green)
![Maven Central Version](https://img.shields.io/maven-central/v/io.github.divinenickname.kotlin.utgen/utgen-core?style=flat&color=green)
[![codecov](https://codecov.io/gh/divinenickname/utgen-kotlin-core/graph/badge.svg?token=4KI0YNW3RF)](https://codecov.io/gh/divinenickname/utgen-kotlin-core)
![Coverage](.github/badges/jacoco.svg)

<img width="100px" src="./logo.svg"  alt="Logo image. Red circle with letter U inside."/><br>
# UTGen-core 

A small library for generating UNIT tests for the Kotlin language. Save time with the code generator by focusing more 
on truly important matters. The library covers common scenarios and generates test cases based on your code. All you 
need to do is provide the expected value.

## Features
- Default case with return value
- Default case for 'Unit' value
- Nullable return types case
- Primitives test case
- Boolean true/false cases

## How to
Add dependency:
```kotlin
implementation("io.github.divinenickname.kotlin.utgen:utgen-core:1.3.1")
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
package com.example.demo1

class MyTestClass {

    fun nonVoid(): ObjClass {
        return ObjClass()
    }
}
```

Library generates code:
```kotlin
package com.example.demo1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MyTestClassTest {
  @Test
  public fun nonVoidTest() {
    val obj = MyTestClass()

    val expected = ObjClass()
    val actual = obj.nonVoid()

    Assertions.assertEquals(expected, actual)
  }
}
```

See more at: [EXAMPLES.md](EXAMPLES.md)

## Depends on

- [ANTLR Kotlin grammar](https://github.com/Kotlin/kotlin-spec)
- [Kotlinpoet for codegeneration](https://github.com/square/kotlinpoet)
- junit-jupiter-api

## See also
- [Idea IDE plugin](https://github.com/divinenickname/utgen-kotlin-idea-plugin)


hi!

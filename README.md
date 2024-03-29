[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
![main branch build](https://github.com/divinenickname/utgen-kotlin-core/actions/workflows/mainbranch-build.yml/badge.svg)
![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fio%2Fgithub%2Fdivinenickname%2Fkotlin%2Futgen%2Futgen-core%2Fmaven-metadata.xml&style=flat&label=sonatype-central&color=green)
![Maven Central Version](https://img.shields.io/maven-central/v/io.github.divinenickname.kotlin.utgen/utgen-core?style=flat&color=green)


<img width="100px" src="./logo.svg"  alt="Logo image. Red circle with letter U inside."/><br>
# UTGen-core 

Simple unit-tests generator for Kotlin language.

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
- Idea IDE plugin: https://github.com/divinenickname/utgen-kotlin-idea-plugin

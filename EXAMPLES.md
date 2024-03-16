# Examples and cases
TC - test case

## Default TC
### Source code:
```kotlin
package com.example.demo1

class MyTestClass {

    fun nonVoid(): ObjClass {
        return ObjClass()
    }
}
```

### Generated code:
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

## Unit TC
### Source code:
```kotlin
package com.example.demo1

class MyTestClass {

    fun voidMethod() {
        println("VOID method")
    }
}
```

### Generated code:
```kotlin
package com.example.demo1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MyTestClassTest {
  @Test
  public fun voidMethod_notThrowTest() {
    val obj = MyTestClass()

    Assertions.assertDoesNotThrow { obj.voidMethod() }
  }
}
```


## Nullable TC
### Source code:
```kotlin
package com.example.demo1

class MyTestClass {

    fun nonVoid(): ObjClass? {
        return ObjClass()
    }
}
```

### Generated code:
```kotlin
package com.example.demo1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MyTestClassTest {
  @Test
  public fun nonVoid_isNullTest() {
    val obj = MyTestClass()

    Assertions.assertNull( obj.nonVoid() )
  }

  @Test
  public fun nonVoidTest() {
    val obj = MyTestClass()

    val expected = ObjClass()
    val actual = obj.nonVoid()

    Assertions.assertEquals(expected, actual)
  }
}
```

## Boolean TC
### Source code:
```kotlin
package com.example.demo1

class MyTestClass {

    fun booleanType(): Boolean {
        return true
    }
}
```

### Generated code:
```kotlin
package com.example.demo1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MyTestClassTest {
  @Test
  public fun booleanType_isTrue() {
    val obj = MyTestClass()

    val actual = obj.booleanType()

    Assertions.assertTrue(actual)
  }

  @Test
  public fun booleanType_isFalse() {
    val obj = MyTestClass()

    val actual = obj.booleanType()

    Assertions.assertFalse(actual)
  }
}
```

## Primitives TC (remove candidate)

Not all primitives are supported. This behavior because some types should generate their own cases. E.g. boolean and string.
There are no specific generators for all primitives, but it will.

Primitives: "Byte", "Short", "Int", "Long", "Float", "Double", "Char"
### Source code:
```kotlin
package com.example.demo1

class MyTestClass {

    fun primitiveType(): Byte {
        return 1
    }
}
```

### Generated code:
```kotlin
package com.example.demo1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MyTestClassTest {
  @Test
  public fun primitiveTypeTest() {
    val obj = MyTestClass()

    val expected:Byte = TODO('Add value here')
    val actual = obj.primitiveType()

    Assertions.assertEquals(expected, actual)
  }
}
```

# Extra cases

## Nullable Boolean TC
### Source code:
```kotlin
package com.example.demo1

class MyTestClass {

    fun booleanType(): Boolean {
        return true
    }
}
```

### Generated code:
```kotlin
package com.example.demo1

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MyTestClassTest {
  @Test
  public fun booleanType_isNullTest() {
    val obj = MyTestClass()

    Assertions.assertNull( obj.booleanType() )
  }

  @Test
  public fun booleanType_isTrue() {
    val obj = MyTestClass()

    val actual = obj.booleanType()

    Assertions.assertTrue(actual)
  }

  @Test
  public fun booleanType_isFalse() {
    val obj = MyTestClass()

    val actual = obj.booleanType()

    Assertions.assertFalse(actual)
  }
}
```

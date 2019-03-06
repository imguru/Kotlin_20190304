// 24_메모이제이션.kt
package ex24

import org.junit.jupiter.api.Test

// 동적 계획법(Dynamic Programming)
//   메모이제이션: 동일한 연산에 대한 결과를 캐시해서, 계산의 성능을 올리는 기법
//   경량(Flyweight) 패턴
//     : 내용이 동일한 객체는 공유하자.

// 코틀린은 메모이제이션의 기법을 쉽게 적용할 수 있도록 제공하고 있습니다.

var cache = mutableMapOf<Int, Long>()

// fib: 순수 함수
//      "입력이 동일하면 함수의 결과값도 동일하다."

// 1 1 2 3 5 ...
/*
fun fib(k: Int): Long = when (k) {
    0, 1 -> 1
    else -> {
        val value = cache[k]
        if (value != null) {
            value
        } else {
            val result = fib(k-1) + fib(k-2)
            cache[k] = result
            result
        }
    }
}
*/

fun fib(k: Int): Long = cache.getOrPut(k) {
    when (k) {
        0, 1 -> 1
        else -> fib(k - 1) + fib(k - 2)
    }
}

// 중복 계산으로 인해 성능이 엄청 나빠진다.
class FibTests {
    @Test
    fun fib1() {
        println(fib(10))
    }

    @Test
    fun fib2() {
        println(fib(50))
    }
}
















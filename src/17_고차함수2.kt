// 17_고차함수2.kt
package ex17

// 함수를 반환하는 함수 (단일 표현식이 매우 유용합니다.)
//  => 실행 시간에 함수를 만드는 기술.

/*
fun foo(): (String) -> String {
    return {  str ->
        str.reversed()
    }
}
*/

/*
fun foo(): (String) -> String = { str ->
    str.reversed()
}

fun main() {
    // 1. foo()를 호출해서 함수를 생성합니다.
    val fn: (String) -> String = foo()

    println(fn("hello"))
}
*/

// 아래 함수는 '런타임'에 정책을 생성하는 함수입니다.
// isOdd, isEven
fun modulo(k: Int, r: Int): (Int) -> Boolean = { value ->
    value % k == r
}

fun main() {
    // val isOdd = modulo(2, 1)
    // val isEven = modulo(2, 0)

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println(list.filter(modulo(2, 1)))
    println(list.filter(modulo(2, 0)))
}




















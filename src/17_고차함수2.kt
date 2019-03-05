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

fun foo(): (String) -> String = { str ->
    str.reversed()
}

fun main() {
    // 1. foo()를 호출해서 함수를 생성합니다.
    val fn: (String) -> String = foo()

    println(fn("hello"))
}
















// 15_확장프로퍼티.kt

// Backing field가 없는 프로퍼티를 마치 메소드처럼 사용할 수 있다.
// => Backfield가 없는 프로퍼티를 기존 클래스에 확장 할 수 있다.

// String: Immutable Object
//     -> 객체가 생성된 이후에 내용이 변경되지 않는다.
val String.lastChar: Char
    get() = this[length - 1]

// StringBuilder: Mutable Object => String
var StringBuilder.lastChar: Char
    get() = this[length - 1]
    set(value) = setCharAt(length - 1, value)

fun main() {
    println("Hello".lastChar)

    val sb = StringBuilder("Money")
    sb.lastChar = 'x'

    println(sb)
}
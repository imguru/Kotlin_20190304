// 16_중위함수.kt
//  Infix Function

// 주의해야 합니다. - Any 타입에 대해서 함부로 확장하지 말아야 합니다.
// : 인자가 하나인 함수에 대해서는 중위 함수로 만들 수 있다.
infix fun Any.pair(other: Any) = Pair(this, other)

fun main() {
    val map = mapOf(
        "name" to "Tom",
        "age" to 42
    )

    // val pair1 = Pair("name", "Tom")
    // val pair2 = Pair("age", 42)

    // val pair1 = "name".pair("Tom")
    // val pair2 = "age".pair(42)
    val pair1 = "name" pair "Tom"
    val pair2 = "age" pair 42

    // Pair는 구조 분해 선언(비구조화 선언)을 지원한다.
    val (key, value) = pair1
    println("$key -> $value")

    val pair3 = "name" to "Tom"

    val x = 0b1100
    // println(x.and(0b0011))
    println(x and 0b0011)
}
// 17_고차함수
//  : 함수를 인자로 받거나, 반환하는 함수

// 고차 함수의 활용
// 1. 다양한 시나리오에서 동작하는 함수의 코드 중복을 없앨 수 있다.

// Kotlin's Collection은 자바의 Collection과 차이가 있습니다.
//   listOf,          mapOf,       setOf
//   List<T>         Map<K, V>     Set<T>

//   mutableListOf  mutableMapOf   mutableSetOf

/*
fun filterEvens(data: List<Int>): List<Int> {
    // MutableList<T>
    val result = mutableListOf<Int>()

    // List<T>: Immutable
    // val result = listOf<Int>()
    // val result = emptyList<Int>()
    for (e in data) {
        if (e % 2 == 0) {
            result.add(e)
        }
    }

    return result
}

fun filterOdds(data: List<Int>): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (e % 2 == 1) {
            result.add(e)
        }
    }

    return result
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5)

    // filterEvens - 짝수
    println(filterEvens(list))
    println(filterOdds(list))
    // filterOdds  - 홀수
}
*/


// Filter 정책만 달라진다.
// Filter의 알고리즘은 변하지 않는다.
//   설계 원칙: 변하는 것과 변하지 않는 것은 분리되어야 한다.
//         "공통성과 가변성의 분리"
//   변하지 않는 전체 알고리즘에서 변해야 하는 정책은 분리되어야 한다.

// 1. Java   - 인터페이스를 통한 동작 파라미터화
// 2. Kotlin - 함수를 통한 정책의 전달


// 방법 1. 동작 파라미터화 설계
/*
interface Predicate<T> {
    fun test(e: T): Boolean
}

fun filter(data: List<Int>, predicate: Predicate<Int>): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (predicate.test(e)) {
            result.add(e)
        }
    }

    return result
}

class EvenPredicate: Predicate<Int> {
    override fun test(e: Int) = e % 2 == 0
}

class OddPredicate: Predicate<Int> {
    override fun test(e: Int) = e % 2 == 1
}

fun filterEvens(data: List<Int>): List<Int> = filter(data, EvenPredicate())
fun filterOdds(data: List<Int>): List<Int> = filter(data, OddPredicate())

fun main() {
    val list = listOf(1, 2, 3, 4, 5)

    val result = filter(list, object: Predicate<Int> {
        override fun test(e: Int): Boolean = e % 2 == 1
    })

    println(result)

    // filterEvens - 짝수
    println(filterEvens(list))
    println(filterOdds(list))
    // filterOdds  - 홀수
}
*/

fun filter(data: List<Int>, test: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()

    for (e in data) {
        if (test(e)) {
            result.add(e)
        }
    }

    return result
}

fun isEven(e: Int) = e % 2 == 0
fun isOdd(e: Int) = e % 2 == 1

// 방법 2. 함수 기반의 정책 전달 방식
fun filterEvens(data: List<Int>): List<Int> = filter(data, ::isEven)
fun filterOdds(data: List<Int>): List<Int> = filter(data, ::isOdd)

fun main() {
    val list = listOf(1, 2, 3, 4, 5)

    // filterEvens - 짝수
    println(filterEvens(list))
    println(filterOdds(list))
    // filterOdds  - 홀수
}







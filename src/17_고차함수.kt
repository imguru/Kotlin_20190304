// 17_고차함수
//  : 함수를 인자로 받거나, 반환하는 함수

// 고차 함수의 활용
// 1. 다양한 시나리오에서 동작하는 함수의 코드 중복을 없앨 수 있다.
// 2. 함수의 재사용성을 극대화!

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

    // 함수를 인자로 전달할 경우, 이름있는 함수를 전달해도 되지만,
    // '이름 없는 코드 블록(또는 무명 함수)'을 전달하는 것도 가능합니다.
    //   => 코틀린은 이름 없는 코드 블록과 이름 없는 함수가 다릅니다.

    // 이름 없는 코드 블록 - 람다(Lambda)
    //  : 람다 블록에서는 일반적으로 return 하면 안됩니다.
    //    => 람다 블록의 최종 결과는 마지막 라인의 결과에 의해서 결정됩니다.
    filter(list, { e: Int ->
        // return e % 2 == 0
        e % 2 == 0
    })

    // 인자의 타입은 컴파일러가 추론 가능하다.
    filter(list, { e ->
        e % 2 == 0
    })

    // 인자가 한개인 경우, it 이라는 키워드로 자동 지정됩니다.
    filter(list, {
        it % 2 == 0
    })

    // 정책 인자가 함수의 마지막 인자로 전달될 경우, 람다를 함수 호출 괄호의 밖에
    // 두는 것이 가능합니다.
    //  -> trailing lambda
    filter(list) {
        it % 2 == 0
    }

    // 많은 다른 언어는 람다와 무명 함수를 동일시합니다.
    // 하지만 코틀린은 구분되어 있습니다.

    // 무명(익명) 함수
    filter(list, fun(e: Int): Boolean {
        return e % 2 == 0
    })

    filter(list, fun(e: Int) = e % 2 == 0)

    // filterEvens - 짝수
    println(filterEvens(list))
    println(filterOdds(list))
    // filterOdds  - 홀수
}







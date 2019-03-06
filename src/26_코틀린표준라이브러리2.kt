// 26_코틀린표준라이브러리2.kt
package ex26_2

// 'Collection'을 다루는 다양한 연산자들
//   -> map, filter, forEach, flatMap, groupBy, zip
// 1. Kotlin - Sequence
// 2. Java 8  - Stream

fun main() {
    val cities = listOf("Seoul", "Suwon", "Daegu", "Busan")

    // map(transform)
    //  : 각각의 요소를 원하는 형태로 변환하는 작업
    val result1: List<Int> = cities.map {
        it.length
    }

    // Nullable
    val result2: List<String?> = cities.map { city ->
        if (city.startsWith("S"))
            city
        else
            null
    }.filter {
        it != null
    }

    val result3: List<String?> = cities.mapNotNull { city ->
        if (city.startsWith("S"))
            city
        else
            null
    }

    // map       vs    flatMap
    // 1 -> X          1  ->  1
    // 2 -> Y          2  ->  1, 2
    // 3 -> Z          3  ->  1, 2, 3

    // flatMap: 결과가 한개가 아니라 여러개 일 경우, 일차원적으로 변환해주는 map
    val result4 = cities.map {
        it.length
    }.flatMap {
        1..it
    }

    println(result4)

//    println(result1)
//    println(result3)


}



















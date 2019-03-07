// 26_코틀린표준라이브러리2.kt
package ex26_2

// Collection을 다루는 표준 연산이 있습니다.
//   => Iterator Pattern

// List(동기, pull)
//   Iterable     <-   Iterator

// => Rx(Reactive Extension)
//   : 비동기 이벤트를 컬렉션 처럼 표준적인 연산을 통해 처리할 수 있다.

// Rx(비동기, push)
//   Observable   ->  Observer
//             map, flatMap, filter ...





// 'Collection'을 다루는 다양한 연산자들
//   -> map, filter, forEach, flatMap, groupBy, zip
// 1. Kotlin - Sequence
// 2. Java 8   - Stream

// map, flatmap, filter, groupBy
/*
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

    // groupBy: 분류 작업
    //     도시의 이름이 'S'로 시작하면 'A' 그룹에 집어넣고 싶고, 아니면 'B' 그룹에 집어넣겠다.
    //   => Map<Char, List<String>>
    val result5: Map<Char, List<String>> = cities.groupBy { city ->
        city[0]
//        if (city.startsWith("S")) {
//            'A'
//        } else {
//            'B'
//        }
    }

    result5.forEach { group: Char, name: List<String> ->
        println("group=$group name=$name")
    }


    // println(result5)

//    println(result1)
//    println(result3)
}
*/

// 코틀린은 'Checked Exception'을 제공하지 않습니다.
//  장점: 더 이상 복잡한 예외 로직으로 인해 코드가 복잡해지지 않는다.
//  단점: 예외로 프로그램이 비정상 종료할 수 있다.
//  => '실패 가능한 연산'은 nullable로 처리한다.

// drop: 원하지 않는 요소를 제거하는 연산
// take: 컬렉션에서 추출하는 연산
// first: 요소의 첫번째를 얻는 연산 - firstOrNull
// last: 요소의 마지막을 얻는 연산  - lastOrNull
// distinct: 중복된 항목을 걸러낸다. - unique
// zip: 두 컬렉션을 조합해서 새로운 컬렉션을 생성합니다.

fun main() {
    val countries = listOf("Korea", "United States", "China", "Japan")
    val codes = listOf("KR", "US", "CN", "JP")

    val result4: List<String> = countries.zip(codes) { country, code ->
        "$country($code)"
    }
    println(result4)

    // println(countries.zip(code))


    val list = listOf(1, 2, 1, 4, 1, 2, 7, 4, 9, 10)
    // val result2 = list.take(3)
    val result2 = list.takeWhile {
        it < 5
    }

    // val result3 = list.drop(3)
    val result3 = list.dropWhile {
        it < 5
    }

    println(result3)



    println(list.distinct())

    val result = list.distinctBy {
        it % 2 == 0
    }

    // 아래 연산의 문제점.
    //  => list가 비어 있는 경우, OutOfIndex 예외가 발생한다.
    // list[0]
    // list[list.count() - 1]
    list.firstOrNull()?.let {
        println("first: $it")
    }

    list.lastOrNull()?.let {
        println("last: $it")
    }

    list.firstOrNull {
        it % 2 == 0
    }?.let {
        println("첫번째 짝수: $it")
    }





    println(result)
}




















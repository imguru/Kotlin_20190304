package ex24_2

import com.google.gson.GsonBuilder

// 기존 함수에 메모이제이션을 적용하는 함수

fun <A, R> ((A)->R).memoized(): (A) -> R {
    // map은 지역 객체 이지만, 람다의 참조에 의해서 수명이 연장된다.
    val map = mutableMapOf<A, R>()
    return { k ->
        map.getOrPut(k) {
            this(k)
        }
    }
}


// Flyweight - 속성이 동일한 객체는 공유하자.
//    주의사항: 메모이제이션을 제대로 적용하기 위해서는,
//     <equals / hashCode>가 정의되어 있어야 합니다.!
//    => data 클래스로 만들면 편리합니다.
data class User(val name: String, val age: Int, val address: String)

// JSON serialization - Gson
fun User.toJson(): String {
    println("User.toJson()")

    /*
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    */

    // 코틀린에서 자바의 빌더 패턴을 사용하는 전형적인 코드 형식
    val gson = GsonBuilder().apply {
        setLenient()
        setPrettyPrinting()
        setVersion(3.14)
    }.create()

    return gson.toJson(this)
}

fun main() {
    val users = listOf(
        User("Tom", 42, "Suwon"),
        User("Tom", 42, "Suwon"),
        User("Tom", 42, "Suwon"),
        User("Tom", 42, "Suwon")
    )

    // 기존 함수에 메모이제이션을 적용해서 새로운 함수를 만들었습니다.
    val toJson = User::toJson.memoized()

    for (e in users) {
        // val json = e.toJson()
        val json = toJson(e)
        // println(json)
    }
}





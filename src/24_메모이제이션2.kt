package ex24_2

import com.google.gson.GsonBuilder

// 기존 함수에 메모이제이션을 적용하는 함수

fun <A, R> ((A)->R).memoized(): (A) -> R {
    val map = mutableMapOf<A, R>()
    return { k ->
        map.getOrPut(k) {
            this(k)
        }
    }
}


// Flyweight
class User(val name: String, val age: Int, val address: String)

// JSON serialization - Gson
fun User.toJson(): String {
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()

    return gson.toJson(this)
}

fun main() {
    val users = listOf(
        User("Tom", 42, "Suwon"),
        User("Tom", 42, "Suwon"),
        User("Tom", 42, ""),
        User("Tom", 42, "")
    )

    // 기존 함수에 메모이제이션을 적용해서 새로운 함수를 만들었습니다.
    val toJson = User::toJson.memoized()

    for (e in users) {
        // val json = e.toJson()
        val json = toJson(e)
        println(json)
    }
}





package ex24_2

import com.google.gson.GsonBuilder
import sun.jvm.hotspot.debugger.Address

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

    for (e in users) {
        val json = e.toJson()
        println(json)
    }
}





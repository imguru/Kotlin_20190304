package ex12

import kotlin.reflect.KProperty

// Property 위임
//  => 프로퍼티의 getter / setter의 동작을 다른 객체에게 위임할 수 있다.

// 이미 잘 만들어진 라이브러리에 '프로퍼티 위임 객체'가 존재합니다.
class SampleDelegate<T>(var field: T) {
    // Field X
    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        println("$thisRef - ${property.name}")
        return field
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        println("$thisRef - ${property.name} to $value")
        field = value
    }
}

class User {
    var name: String by SampleDelegate("Tom")
    var address: String by SampleDelegate("Suwon")
}

fun main() {
    val user = User()
    val name = user.name  // user.getName()
//    user.name = "Tom"
//
//    user.address = "Suwon"

    println(user.address)
}


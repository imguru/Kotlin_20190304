package ex10

import java.util.*

// OOP         -> Reference
// Functional  -> Value


// Data Object / Value Object
// 1. equals / hashCode
// 2. toString()
// 3. 깊은 복사
//    java: clone()

class User(val name: String, val age: Int) {
    // 비구조화 선언을 지원하는 방법
    //  => '연산자 오버로딩'을 통해 제공할 수 있습니다.
    operator fun component1(): String {
        return name
    }

    operator fun component2(): Int {
        return age
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(name, age)
    }

    override fun toString(): String {
        return "User{name=$name,age=$age}"
    }
}


// 1. Kotlin 에서는 Clone을 사용할 수 없습니다.
// => data 클래스에서는 copy 라는 기능을 제공합니다.
// 2. finalize도 존재하지 않습니다.
//    => 객체가 GC에 의해 수거되는 시점에 호출되는 메소드
//    1) 부모가 finalize을 제대로 정의하고 있지 않다면, 문제가 발생한다.
//    2) 호출이 안될 수도 있다.
//    3) 호출 시점이 명확하지 않다.

// data class User(val name: String, val age: Int)

fun main() {
    val user1 = User("Tom", 42)
    val user2 = User("Tom", 42)

    // val user2 = user1.copy()

    // == -> equals
    println(user1 == user2)

    // toString
    println(user1)

    // copy
    // val user3 = user1.copy(name = "Suwon")

    val users = listOf(user1, user2)

    // for (user in users) {
    //    println("${user.name} ${user.age}")
    // }

    // 비구조화 선언을 이용한 순회
    for ((name, age) in users) {
        println("$name $age")
    }
}










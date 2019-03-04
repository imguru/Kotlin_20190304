package ex5

// 1. Property 정의
//     Field + Accessor Method

// val: field + getter
// var: field + getter / setter

/*
class User {
    var name: String = "Tom"

    // Custom Getter / Setter도 제공할 수 있습니다.
    var age: Int = 0
        private set(value) {
            // 주의: 재귀 호출이 발생할 수 있으므로, 주의해야 합니다.
            // age = value + 10
            field = value + 10
        }
        get() {
            return field * 10
        }
}

fun main() {
    val user = User()
    println(user.name)   // getter를 호출합니다.

    user.name = "Bob"
//    user.age = 42

    val person = Person()
    person.name = "Tom"

    println(person.name)
}
*/

// 2. Backing field가 없는 프로퍼티
//    : 필드를 생성하지 않고, 접근자 메소드만 제공한다.
//   => 메소드처럼 사용하고 싶다.
class User(var firstName: String, var lastName: String) {
    var fullName: String
        get() {
            return "$firstName, $lastName"
        }
        set(value) {
            val arr = value.split(" ", ", ")

            firstName = arr[0]
            lastName= arr[1]
        }
}


fun main() {
    val user = User("Chansik", "Yun")
    println(user.fullName)

    user.fullName = "Gildong, Hong"
    println(user.fullName)
}
















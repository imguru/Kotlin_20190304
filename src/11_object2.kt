package ex11_2
// 11_object2.kt

// 2. Companion object
//   - 코틀린에서는 static 키워드가 존재하지 않습니다.
//   - companion object에 이름을 부여해도 코틀린에서는 아무런 영향이 없습니다.
class Point(val x: Int, val y: Int) {
    // static
    // companion object Utils {
    companion object {
        var n: Int = 0

        fun newPoint(): Point {
            ++n
            println("${n}번째 객체")
            return Point(0, 0)
        }
    }
}

// 활용: 정적 팩토리 메소드
//  -> '생성자'를 직접하지 말고, 정적 팩토리를 이용하면 좋다.
//   1) 생성자 오버로딩은 한계가 있다.
//    => 객체가 어떤 형태로 생성되는지 이름을 통해 표현할 수 없다.
//   2) 코드의 가독성이 떨어진다.
//   3) 객체 생성의 정책을 변경할 수 없다.

fun getFacebookName(id: Int): String {
    return "$id@facebook"
}

// private primary constructor
class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String): User {
            return User(email.substringBefore("@"))
        }

        fun newFacebookUser(accountId: Int): User {
            return User(getFacebookName(accountId))
        }
    }

//    constructor(email: String) {
//        nickname = email.substringBefore("@")
//    }
//
//    constructor(facebookAccountId: Int) {
//        nickname = getFacebookName(facebookAccountId)
//    }
}


/*
fun main() {
    val email = "chansigi@imguru.co.kr"
    val facebookId = 42

    // val user1 = User(email)
    // val user2 = User(facebookId)
    val user1 = User.newSubscribingUser(email)
    val user2 = User.newFacebookUser(facebookId)


    // val p1 = Point.newPoint()
    // val p2 = Point.newPoint()
    // val p3 = Point.newPoint()

    //      new Point(0, 0);
    // val p = Point(0, 0)

}
*/

// 동반 객체는 '객체' 이기 때문에 인터페이스를 구현하는 것도 가능합니다.
// JSON -> Map
interface MapFactory<T> {
    fun fromMap(map: Map<String, Any>): T
}

data class Person(val name: String, val age: Int) {
    companion object: MapFactory<Person> {
        override fun fromMap(map: Map<String, Any>): Person {
            val name = map["name"] as String
            val age: Int = map["age"] as Int

            return Person(name, age)
        }
    }
}

fun<T> loadFromMap(map: Map<String, Any>, factory: MapFactory<T>): T {
    return factory.fromMap(map)
}

fun main() {
    val json = mapOf(
        "name" to "Tom",
        "age" to 42
    )

    // val person = Person.fromMap(json)
    val person = loadFromMap(json, Person)
    println(person)
}
































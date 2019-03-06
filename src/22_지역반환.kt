// 22_지역반환.kt
// 지역 반환 / 비지역 반환

package ex22

class Person(val name: String)

/*
// Alice 라는 이름의 객체를 찾는 함수
fun lookForAlice(people: List<Person>) {
    // 아래의 반복 구문은 명령형 프로그래밍의 방식입니다.
    //  => '선언적 형태'로 작성하는 것이 가능합니다. - 함수형 프로그래밍
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!!!")
            return
        }
    }

    println("Cannot find Alice!")
}
*/

// Alice 라는 이름의 객체를 찾는 함수
//  : 코틀린을 제외한 다른 언어들은 아래 처럼 코드를 작성하면 동작이 달라집니다.

// 1. 람다는 '비지역 반환'을 지원합니다.
//    => 람다 구문에서 return을 사용할 경우, 람다 구문에 대한 반환이 아니라
//       람다 구문을 사용한 함수에 대한 반환으로 취급된다.

// 2. 지역 반환: 람다 블록에 대한 반환이 필요하다.
//             람다 블록 안에서 오류 처리 등을 통해 람다 블록을 빠져나와야 되는 경우...
fun lookForAlice(people: List<Person>) {
    people.forEach { person ->
        if (person.name == "Alice") {
            println("Found!!!")
            // return  - 비지역 반환
            return@forEach  // 지역 반환
        }
    }

    println("Cannot find Alice!")
}

fun main() {
    val people = listOf(
        Person("Tom"),
        Person("Bob"),
        Person("Alice")
    )

    lookForAlice(people)



}
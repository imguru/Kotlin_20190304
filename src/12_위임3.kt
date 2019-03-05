package ex12_3

import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

// 12_위임3.kt

// 코틀린이 이미 제공하는 표준 프로퍼티 델리게이트가 있습니다.
// 1. lazy
//   : 지연 초기화
//   => 바로 생성하는 것이 아니라, 처음 필요한 시기에 생성을 하겠다.
//   => 객체의 초기화에 악영향을 미치는 프로퍼티를 필요한 시점에 생성하는 기법

class HeavyObject {
    init {
        println("HeavyObject를 생성 중...")
        TimeUnit.SECONDS.sleep(3)
        println("HeavyObject를 생성 완료되었습니다....")
    }

    fun foo() {
        println("HeavyObject foo")
    }
}

class User {
    // val heavyObject: HeavyObject = HeavyObject()
    // lateinit var heavyObject: HeavyObject
    val heavyObject: HeavyObject by lazy {
        println("lazy 블록 실행")
        HeavyObject()
    }

    // lateinit
    //  - var 에 대해서만 사용할 수 있다.
    //  - 초기화하지 않고 사용할 경우,
    //    UninitializedPropertyAccessException 발생합니다.
    //  - setter / getter의 재정의가 불가능합니다.
    //  - primitive 타입에 대해서는 사용할 수 없습니다.
    //    (Int, Long, Float, Double, Char, Byte)

    // lazy: Property 위임 객체
    //  - val에 대해서 사용할 수 있다.
    //  - 멀티 스레드에서 동시에 접근할 경우, 안전하게 한번만 초기화됨을 보장합니다.

    // fun init() {
    //    heavyObject = HeavyObject()
    // }

    fun foo() {
        heavyObject.foo()
    }
}

/*
fun main() {
    println("Program 시작")
    val user = User()     // !!
    println("User 객체 생성 완료")

    // user.init()
    user.foo()
}
*/

// 2. 프로퍼티의 값의 변경에 따라서 수행되는 로직을 캡슐화할 수 있습니다.
//    iOS: KVO(Key-Value Observation)
//    Kotlin: Delegates.observable

// MVC(Model-View-Controller)
// View
class TextView {
    var text: String by Delegates.observable("") { _, old, new ->
        println("TextView.text: $old -> $new")
    }
}

// Controller
class Activity {
//    var email: String = ""
//        set(value) {
//            emailTextView.text = value
//            field = value
//        }

    var email: String by Delegates.observable("unnamed") { prop, old, new ->
        // 값이 변경되었을 때 호출되는 블록
        println("$old -> $new")
        emailTextView.text = new
    }


    // model의 데이터가 변경될 때마다, emailTextView.text도 변경되어야 한다.
    val emailTextView = TextView()
}

/*
fun main() {
    val activity = Activity()

    activity.email = "chansik.yun@gmail.com"
    activity.email = "gildong.hong@gmail.com"
}
*/

// 3. vetoable: 사용자가 지정한 validation 규칙에 부합되지 않으면 값이 변경되지 않습니다.
class Person {
    val myRule = Delegates.vetoable("unnamed") { _, old, new ->
        // validation 규칙
        new.length >= 5
    }

    // username은 반드시 5글자 이상이어야 한다.
    var username: String by myRule
    var email: String by myRule
}

fun main() {
    val person = Person()

    person.username = "Tom"
    println(person.username)

    person.username = "Tommy"
    println(person.username)
}


































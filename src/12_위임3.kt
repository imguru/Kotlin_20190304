package ex12_3

import java.util.concurrent.TimeUnit

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

fun main() {
    println("Program 시작")
    val user = User()     // !!
    println("User 객체 생성 완료")

    // user.init()
    user.foo()
}
















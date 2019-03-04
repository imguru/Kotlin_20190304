import java.util.*

// 1. main 함수를 만드는 방법

// 2. 함수를 만드는 방법
//  fun 함수이름(파라미터 이름: 타입, ...): 반환 타입
//   - 자바에서는 전역함수를 만들 수 없다.
//    => 공통적인 라이브러리 함수를 별도의 클래스에 모아놓았다.
//       (정적 메소드의 집합 클래스)
//      ex) Arrays, Collections, Objects
//   - 코틀린에서는 전역함수를 만들 수 있다.
//    => 별도의 파일에 전역 함수를 모아 놓는다.
//   - 코틀린 - 함수형 언어(Functional Language)
//    => 순수 함수(pure function)
//      : 함수의 입력값이 동일하면, 반드시 결과값도 동일해야 한다.
//    => Java: void -> Kotlin: Unit
/*
fun add(a: Int, b: Int): Int {
    return a + b
}

fun foo() {
}

fun main() {
    // println("a + b = " + add(10, 20))
    println(foo())
}
*/

// 3. 타입 시스템
//   1) Java
//     - Primitive Type
//       int, char, byte, double ...
//       1) Collection에 저장할 수 없다.
//       2) 필드와 메소드를 가질 수 없다.

//     - Reference Type
//       class, interface, enum, Array ...

//   2) Kotlin
//     - 모든 것은 객체(필드 + 메소드)이다.
//       : 코틀린 컴파일러가 원시 타입으로 사용할지, 참조 타입으로 사용할지 판단한다.
//       => 성능의 저하를 최소화한다.
//     - 강타입 언어이다.
//       => 강력한 타입 제약이 있다.
//       => 암묵적인 타입 캐스팅이 거의 허용되지 않는다.

// 4. 변수를 선언하는 방법
//     - var: 변수
//       : User user = new User();
//     - val: 상수
//       : final User user = new User();
/*
fun main() {
    // val or var
    // var: int n
    // val: final int n
    val n1 = 42
    val n2: Long = 42

    var n3: Long = 0
    n3 = n1.toLong()
}
*/

// 5. 기본 연산자(Bit 연산자)
//  Java
//     <<, >>(Arithmetic Shift), >>>(Logical Shift)
fun main() {
    // 2진수 리터럴 문법
    val n = 0b1100
    println(n)

    println(n shl 1)  // <<
    println(n shr 1)  // >>
    println(n ushr 1) // >>>

    println(n and 0b1000) // &
    println(n or 0b1000)  // |
    println(n xor 0b1000) // ^
    println(n.inv()) // ~
}
























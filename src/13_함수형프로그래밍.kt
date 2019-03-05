// 13_함수형프로그래밍.kt
package ex13

//  * Functional Programming
//    Haskell / Clojure

//    Java8
//    C++11/14
//    'Kotlin' / Swift

// 함수형 프로그래밍 언어
//   => 함수를 일급 시민(first class citizen)으로 취급한다.
//    1) 함수를 변수에 담을 수 있어야 한다.
//    2) 함수를 인자로 전달할 수 있어야 한다.
//    3) 함수를 반환할 수 있어야 한다.
//    4) 실행 시간에 함수를 생성할 수 있어야 한다.
//    5) 익명으로 함수를 생성할 수 있어야 한다.
/*
fun add(a: Int, b: Int): Int {
    return a + b
}
*/

// - 단일 표현식 함수
//   : 간단한 한줄 정도의 함수를 좀 더 간결하게 표현할 수 있는 문법
//   -> 반환 타입 추론이 가능하다.
fun add(a: Int, b: Int) = a + b

fun sub(a: Int, b: Int) = a - b
fun foo(a: Int, b: Double, c: Char) = "foo - $a $b $c"

// - 함수의 타입
//   : 함수의 타입은 함수의 시그니쳐(signature)에 의해서 결정된다.
//   : 함수의 시그니쳐 - 함수의 인자의 타입과 개수, 반환 타입

// add: (Int, Int) -> Int
// sub: (Int, Int) -> Int
// foo: (Int, Double, Char) -> String

// main: (Array<String>) -> Unit

// :: <- 전역의 네임 스페이스
// KFunctionN: Kotlin의 JVM 타입
/*
fun main(args: Array<String>) {
    //  1) 함수를 변수에 담을 수 있어야 한다.
    //     : C언어의 함수 포인터 개념과 유사하다.
    // var fp: (Int, Int) -> Int = ::add
    var fp = ::add
    fp = ::sub


    // println(fp(10, 20))
    println(fp.invoke(10, 20))

    // var fp2: (Int, Double, Char) -> String = ::foo
    val fp2 = ::foo
    println(fp2(42, 3.14, 'A'))

    // KFunction{N}
    // N: 인자의 개수
    // KFunction3<ARG1, ARG2, ARG3, RET>

    val fp3: ((Int, Double, Char) -> String)? = null
    // fp3 = ::foo

    // fp3가 null이 아니면, 호출한다.
    // fp3?(42, 3.14, 'A')
    fp3?.invoke(42, 3.14, 'A')
}
*/

/*
fun printArea(width: Int, height: Int) {
    // - 지역 함수를 만드는 것도 가능합니다.
    fun calcArea(width: Int, height: Int) = width * height

    val area = calcArea(width, height)
    println("The area is $area")
}
*/

fun printArea(width: Int, height: Int) {
    // 지역 함수를 만들면 클로져(closure)를 지원합니다.
    //  - Closure: 다른 함수의 컨텍스트에 있는 변수에 암묵적으로 접근 가능한 기술

    fun calcArea() = width * height

    val area = calcArea()
    println("The area is $area")
}

fun main() {
    printArea(100, 30)
}






























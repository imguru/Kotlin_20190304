// 23_Nullable.kt
package ex23

// Nullable
//   Optional, Maybe
// => 'Null 포인터'를 안전하게 다루는 기술

// Java             ->      Kotlin
//   String                 String? String
//                           : String으로 사용하는데, null이 전달되면
//                             프로그램에 비정상 종료가 발생할 수 있다.

//   @NonNull String        String
//   @Nullable String       String?

fun getName(): String? = null

var result2 = getName()

fun main() {
    // 1. 일반 타입에 대해서 null을 대입하는 것은 허용되지 않습니다.
    // val name: String = null
    // val name: String? = null

    // val result: String? = getName()
    val result: String? = getName()

    // 2. Nullable 타입에 대해서는 반드시 접근하기 전에 null 체크를 수행해야 합니다.
    if (result != null) {
        println(result.length)   // Smart cast
    }

    /*
    if (result2 != null) {
        println(result2.length)   // 스마트 캐스트가 적용될 수 없다.
                                  // 1) val

    }
    */

    // 2) 원자적으로 널 체크(안전한 널 참조 연산)
    // println(result2?.length)
    // let 구문
    //  => 수행한 객체가 null이 아니면 수행할 구문을 지정할 수 있다.
    result2?.length?.let {
        println("let 블록 실행")
        println(it)
    }

    // 아래가 null 이면 프로그램이 죽도록 단언하고 싶다. - assertion
    //  => 강제 참조, null 이면 예외가 발생합니다.
    println(result!!.length)
}













package ex25

import java.lang.StringBuilder

// 25_범위지정함수
//   let, with, apply, use

// 1. let
/*
fun sendEmail(to: String) = println("Send email to $to")
class User {
    var email: String? = null
}

fun main() {
    val user = User()

    // sendEmail(user.email)
    // if (user.email != null) {
    //    sendEmail(user.email)
    // }

    // user의 email이 null이 아니면 수행하고자 로직을 let을 통해
    // 블록으로 지정할 수 있습니다.
    //  => 안전한 널 참조 연산과 함께 사용해야 합니다.
    user.email?.let {
        sendEmail(it)
    }
}
*/

// 2. with / apply
// 'A' ~ 'Z' 하나의 문자열로 만들어주는 함수
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z')
        result.append(letter)
    result.append("\n")
    return result.toString()
}

// with - 수신 객체 지정(this) 람다
// with 결과 - 람다 블록에 의해서 결정된다.

fun alphabet_with(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
    toString()
}

// apply 결과 - 수신 객체가 결과가 됩니다.

fun alphabet_apply(): String = StringBuilder().apply {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
}.toString()

fun alphabet_buildString(): String = buildString {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
}

//  객체 초기화의 과정이 복잡한 경우, 많이 사용합니다.
class Sample {
    fun foo() {}
    fun goo() {}
    fun hoo() {}
}

/*
fun main() {
    val sample = Sample().apply {
        foo()
        goo()
        hoo()
    }

    println(alphabet())
    println(alphabet_with())
}
*/

// Fragment / RecyclerViewAdapter
//   with: '반복되는 참조연산'의 중복을 없앨 때 사용합니다.

// Nothing - 함수가 반환을 아예 하지 않을 경우 사용하는 타입
fun foo(): Nothing {
    throw Exception("...")
}

// use - Java's Try with Resources 대체합니다.
fun main() {
    // 비메모리 자원을 안전하게 해지하는 구문
    // use: AutoCloseable Extension Function
    val resource = MyResource()

    resource.use {
        println("Kotlin....")
        resource.foo()
    }
}


























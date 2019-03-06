package ex25

import java.lang.StringBuilder

// 25_범위지정함수
//   let, with, apply

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
fun alphabet_with(): String = with(StringBuilder()) {
    for (letter in 'A'..'Z')
        append(letter)
    append("\n")
    toString()
}


fun main() {
    println(alphabet())
    println(alphabet_with())
}

































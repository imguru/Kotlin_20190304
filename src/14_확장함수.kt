package ex14

import java.lang.StringBuilder

// 14_확장함수.kt
//   => Extension Function
//   => 코틀린 라이브러리의 핵심!
//   => 기존 자바 API를 재작성하지 않고도, 코틀린 제공하는 편리한 기능을 사용할 수 있도록 해주었다.

//  C#: 확장 함수
//  Swift: Extension
//  ObjC: Category
//  Javascript: Prototype

//  상속
//   : 기존 클래스의 기능을 확장해서 사용한다.
//   => 수직 확장

//  문제점
//   1) 깨지기 쉬운 기반 클래스의 문제
//   2) 상속 금지되어 있는 클래스에 대해서는 기능을 확장할 수 없다.

//  해결 방법: 수평 확장을 통해 기능을 확장하자.!!

// 문자열의 마지막 글자를 추출하는 함수
// fun lastChar(text: String): Char = text[text.length - 1]

/*
class MyString: String() {
    // ...
}
*/

// Anko => Android Kotlin(Jetbrains)

// Image img
// img.glide.processing()
// String. : 수신 객체 타입
//   this  : 수신 객체 참조
/*
fun String.lastChar(): Char = this[this.length - 1]
// private / protected 필드는 접근이 불가합니다.

fun main() {
    val str = "Hello"
     str.split(",")
     str.replace("a", "b")

    // println(lastChar(str))
    println(str.lastChar())
}
*/

// 1 2 3 -> joinToString -> [1, 2, 3]

// 아래의 함수를 Collection<T>라는 타입에 대해서 확장하고 싶다.
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0)
            result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(10, 20, 30)
    val result = joinToString(
        collection = list,
        separator = ", ",
        prefix = "[",
        postfix = "]"
    )

    println(result)
}



























package ex6

// 6_접근지정자.kt
//  * Java
//    private
//    package-default
//     -> 같은 패키지에서 접근이 가능하다.
//      : 다른 모듈에서 같은 패키지 이름을 사용하면, 마음껏 접근이 가능하다.

//    protected
//     -> package 레벨에서 접근이 가능하다.
//    public

//  * Kotlin
//    private
//    internal
//     : 같은 모듈에서만 접근이 가능합니다.
//    protected
//     : 오로지 자식 클래스를 통해서만 접근이 가능합니다.

//                       Windows         Jetbrains IDEs
// Project      ->     Solution     ->    Project
//   A.exe                Project            Module
//   a.lib                Project            Module
//   b.lib                Project            Moudle

// Kotlin은 internal에 대해서는 package로 처리하는데,
//  => 이름을 맹글링합니다.

// 같은 모듈에서만 접근 가능한 클래스
internal class User {
    // protected val money = 42
}

// 같은 모듈에서만 접근 가능한 함수
internal fun foo() {

}

// class나 함수 앞에 붙이는 private => 같은 파일에서만 접근 가능합니다.
private class Person
private fun goo() {

}


fun main() {
    val user = User()
    // println(user.money)
}






















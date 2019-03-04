package ex8

/*
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// Expression(식) vs Statement(문)
//  : Expression은 결과가 있지만, Statement는 결과가 없다.

// when -> Expression

// 1. switch 존재하지 않습니다.
//   => when

// 아래 코드의 문제점: 새로운 Expr 기반의 클래스가 생성될 경우, 예외가 발생한다.
//        해결 방법: Num, Sum 이외의 Expr 하위 클래스가 존재하면 안된다.
fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else ->
            throw IllegalArgumentException("Unknown")
    }
}
*/

// sealed
//   : 소프트 키워드 - class의 키워드와 같이 사용되어야 한다.

sealed class Expr {

}

// 같은 파일 안에서 상속을 허용합니다.
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

// class Null : Expr()
//  => 새로운 클래스 확장시에 when 식에서 오류가 발생한다.
//    코틀린 1.0: 봉인된 클래스는 반드시 Expr의 중첩 클래스이어야 한다.
//    코틀린 1.2: 같은 파일로 좀 더 완화되었다.

fun eval(e: Expr): Int {
    return when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
    }
}

fun main() {
    val sum = Sum(Num(10), Num(32))
    println(eval(sum))
}













// 19_커링.kt

// 커링(Currying)
//  : 다중 인수를 갖는 함수를 단일 인수를 갖는 함수들의 함수열로 바꾸는 것을 의미한다.

// 1. 함수 실행을 지연할 수 있다.

//               커링
// sum(10, 20)   ->    sum(10)(20)   ->  30

/*


fun main() {
    // val result = sum(10, 20)
    // println(result)

    // val result = sum(10)(20)
    // println(result)
    val s = sum(10)
    println(s(20))
}
*/

fun sum(x: Int, y: Int) = x + y
fun sum3(x: Int, y: Int, z: Int) = x + y + z
fun diff(x: Int, y: Int) = x - y

//fun sum(x: Int): (Int) -> Int = { y ->
//    x + y
//}

// 인자가 2개인 함수에 대해서 currying 지원을 추가해 봅시다.
fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1 ->
    { p2 ->
        this(p1, p2)
    }
}

fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1 ->
    { p2 ->
        { p3 ->
            this(p1, p2, p3)
        }
    }
}

fun main() {
     val csum = ::sum.curried()
     println(csum(10)(20))

    val csum3 = ::sum3.curried()
    val result = csum3(10)(20)(30)

    println(result)
}

























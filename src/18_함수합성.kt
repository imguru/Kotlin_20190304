// 18_함수합성.kt
//  Function Composition

//  f(x) -> y
//  g(y) -> z

//  x -> f(x) -> y -> g(y) -> z
//  x -> f(x) * g(y) -> z

// f(x).compose(g(y))

// x: String
// y: Int
// z: Int

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    val y = f(x)
    val z = g(y)
    z
}
*/

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = { x ->
    g(f(x))
}
*/

/*
fun compose(f: (String) -> Int, g: (Any) -> Int): (String) -> Int = {
    g(f(it))
}
*/

/*
fun<A, B, C> compose(f: (A) -> B, g: (B) -> C): (A) -> C = {
    g(f(it))
}
*/

infix fun<A, B, C> ((A) -> B).compose(g: (B) -> C): (A) -> C = {
    g(this(it))
}

fun main() {
    val f: (String) -> Int = String::length
    val g: (Any) -> Int = Any::hashCode

    // 문자열의 길이를 통해 해시값을 구하는 함수를 실행시간에 새롭게 생성할 수 있습니다.
    // val fog = compose(f, g)
    // val fog = f.compose(g)
    val fog = f compose g

    println(fog("Bob"))
    println(fog("Tom"))
}

/*
class MyString {
    var name: String = ""

    // 메소드의 시그니쳐는 첫번째 인자로 자신의 타입이 온다.
    // (MyString) -> Int
    fun length(): Int {
        // this.name
        return 42;
    }
}

fun main() {
    // 문자열의 길이를 구하는 함수
    val s = "Hello"

    // 객체가 이미 결정되었다.
    // 첫번째 인자로 객체를 전달할 필요가 없다.
    val g: () -> Int = s::length

    // 어떤 객체를 대상으로 호출할 지 결정되지 않았다.
    // => 첫번째 인자로 객체의 주소가 전달되어야 한다.
    val f: (String) -> Int = String::length

    // val str = "Hello"
    // println(str.length)
}
*/



















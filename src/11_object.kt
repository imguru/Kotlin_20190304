// 11_object.kt

// 1. object declaration
//   -> Singleton
//   정의: 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 참조를 얻을 수
//        있어야 한다.

//  - Lazy Initialization
//  - 멀티 스레드 안전하게 생성된다.

// object Cursor(val name: String)  // error!
// => 생성자를 정의할 수 없습니다.
object Cursor {
    init {
        println("Cursor()")
    }

    // 프로퍼티와 메소드를 정의할 수 있습니다.
    var x: Int = 0
    var y: Int = 0

    fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
        println("moved - ($x, $y)")
    }
}

fun main(args: Array<String>) {
    Cursor.move(10, 20)
}

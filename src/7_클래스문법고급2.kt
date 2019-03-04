package ex7_2

import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

// 내부 클래스(Inner class) vs 중첩 클래스(Nested class)
/*
class A {
    class Inner {}
    static class Nested {} - Kotlin default
}
*/

interface State : Serializable
interface View {
    var currentState: State
}

class Button : View {
    // default 가 중첩 클래스이기 때문에, inner 를 통해 내부 클래스로 만들 수
    // 있다.
    inner class ButtonState(val x: Int, val y: Int) : State

    override var currentState: State
        get() {
            return ButtonState(10, 32)
        }
        set(value) {
            // ...
        }
}

// Kotlin은 더 이상 Checked Exception을 지원하지 않습니다.
fun main() {
    val button = Button()

    val fos = FileOutputStream("state.out")
    val oos = ObjectOutputStream(fos)

    oos.writeObject(button.currentState)
}









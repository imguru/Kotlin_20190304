// 3. 11_object3.kt
package ex11_3

// 무명 객체(Anonymous object)
interface MouseAdapter {
    fun mouseClicked()
    fun mouseEntered()
}


class Window {
    var mouseAdapter: MouseAdapter? = null

    fun click() {
        // var를 사용할 경우, null 체크 시점에는 null이 아닐 수 있지만,
        // 그 이후에는 null일 수 있다.
        // => Smart Cast가 제대로 동작하지 않는다.
        // if (mouseAdapter != null) {
        //    mouseAdapter.mouseClicked()
        // }

        // null 체크와 메소드 호출을 원자적으로 하는 방법.
        mouseAdapter?.mouseClicked()
    }

    fun enter() {
        mouseAdapter?.mouseEntered()
    }
}

class MyMouseEvent: MouseAdapter {
    override fun mouseClicked() {
        println("MyEvent - mouseClicked")
    }

    override fun mouseEntered() {
        println("MyEvent - mouseEntered")
    }

}

fun main() {
    val window = Window()
    // window.mouseAdapter = MyMouseEvent()

    val n = 42

    // 이벤트 리스너가 하나만 사용된다면, 무명 객체가 좋다.
    // => 코틀린에서 무명 객체를 만드는 방법
    window.mouseAdapter = object : MouseAdapter {
        override fun mouseClicked() {
            println("MyEvent - mouseClicked - $n")
        }

        override fun mouseEntered() {
            println("MyEvent - mouseEntered")
        }
    }

    window.click()
    window.enter()
}














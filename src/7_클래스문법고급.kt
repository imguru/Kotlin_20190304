package kex7

// public final class Car
// final: 상속 금지 / 오버라이드 금지가 기본입니다.

// open: 상속 가능 클래스
open class Car(val name: String)
interface Go

// class Truck extends Car implements Go {
//    Truck(String name) {
//        super(name)
//    }
// }

// 1. 클래스를 상속하는 방법과 인터페이스를 구현하는 방법
class Truck(name: String) : Car(name), Go

// 2. Interface
//   장점: 약한 결합을 통한 교체가능한 유연한 설계
//   단점: 변화에 대응하기 어렵다.
//
//   * 자바와 코틀린의 차이점
//    1) Property도 인터페이스로 명세화 할 수 있습니다.
//    2) 기본 구현을 제공할 수 있습니다.
//       => default method(Java 8) / defender method
//       추상 클래스는 필드(프로퍼티)를 제공할 수 있습니다.
interface Clickable {
    fun click()
    val name: String

    fun showOff(): String {
        return "I'm Clickable"
    }
}

interface Focusable {
    fun showOff(): String {
        return "I'm Focusable"
    }
}

// 상속 / 오버라이딩 - open / abstract / interface
//  : Effective Java
//  => 기반 클래스를 변경하는 경우 하위 클래스의 동작이 예상하지 못하게 변경될 수 있다.
//  => 상속을 위한 설계와 문서를 갖추거나, 그럴 수 없다면 상속을 금지해라.

// 부모 인터페이스가 동일한 기본 구현을 제공하고 있을 경우, 자식 클래스는
// 반드시 오버라이딩 해야합니다.
//  => 다이아몬드 상속 문제
open class Button : Clickable, Focusable {
    // Backing 필드가 없는 프로퍼티입니다.
    override val name: String
        get() {
            return "Button"
        }

    override fun click() {
        println("clicked")
    }

    // 오버라이딩 금지가 기본입니다.
    // open: 오버라이딩 허용한다.
    open fun move() {
        println("Button move")
    }

    // 인터페이스가 제공하는 기능을 오버라이딩을 통해 변경가능합니다.
    override fun showOff(): String {
        // super<Clickable>.showOff()
        return super<Focusable>.showOff()

        // return "I'm Button"
    }
}

class MouseButton : Button() {
    override fun move() {
        println("MouseButton move")
    }
}



fun main() {
    val mouse: Button = MouseButton()
    mouse.move()
}


/*
// abstract
abstract class User {
    abstract fun foo()

    open fun name() {

    }
}

class Admin : User() {
    override fun foo() {
    }

    override fun name() {
    }
}
*/












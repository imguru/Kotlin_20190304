package ex12

// Delegation Pattern
//   : 하나 이상의 메소드 호출을 다른 객체에게 위임한다.

// 1) class 위임
//   : 객체에게 기능을 위임하는 방법

// 2) 프로퍼티 위임

// OOP 재사용
// 1. 상속
//     : 부모의 기능을 암묵적으로 재사용.
//     => 상속을 재사용의 목적으로 사용하는 것은 좋지 않다.
//        상속: 다형성을 목적으로 해야 한다.
/*
open class A {
    fun foo() {}
}

class B : A()
*/
// 2. 위임(포함, 합성)
//     : 자신의 메소드를 다른 객체를 통해 명시적으로 구현함.
/*
class A {
    fun foo() {}
    fun goo() {}
}

class B {
    val a: A = A()
    fun foo() {
        a.foo()
    }
}

fun main() {
    val b = B()
    b.foo()
    // b.goo()
}
*/
interface UIElement {
    fun getHeight(): Int
    fun getWidth(): Int
}

class Rectangle(val x1: Int, val x2: Int, val y1: Int, val y2: Int) : UIElement {
    override fun getHeight(): Int {
        return y2 - y1
    }

    override fun getWidth(): Int {
        return x2 - x1
    }
}

// by: 암묵적으로 기능을 재사용할 수 있는 키워드
//  : UIElement의 메소드의 기능을 rectangle 객체에게 위임하겠다.
//   1) 합성을 상속만큼 편리하게 사용할 수 있게 해준다.
//   2) 프로퍼티로 만들 필요가 없다.
class Panel(rectangle: Rectangle) : UIElement by rectangle
// {
//    override fun getHeight(): Int {
//        return rectangle.getHeight()
//    }
//
//    override fun getWidth(): Int {
//        return rectangle.getWidth()
//    }
// }

fun main() {
    val panel = Panel(Rectangle(10, 100, 30, 100))
    println(panel.getWidth())
    println(panel.getHeight())
}






























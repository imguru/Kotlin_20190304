// 21_inline2.kt
package ex21

// Generics
//  1. C++ Template: 타입 인자로 받은 T에 대한 코드를 생성한다.
//    => 타입에 대한 인자가 변경될때마다 코드를 생성해야 하므로, '코드 메모리 사용량'이
//       올라간다.
//  2. Java: 타입 소거(Type erasure)
//    => 타입에 따라서 코드를 생성하지 않기 때문에, 코드 메모리 사용량이 적다.
//       아래 같은 기술을 사용할 수 없다.

//    => 함수를 호출하지 않고, 인라인화 하면은 아래 같은 기술을 사용할 수 있다.
//       <reified T>
inline fun<reified T> isA(value: Any) = value is T

open class Car
class Truck : Car()

fun main() {
    val car = Car()

    println(car is Truck)
    println(isA<Truck>(car))
}













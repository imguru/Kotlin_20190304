// 3_클래스문법2.kt

// 문자열을 처리하는 방법
/*
class Address(val address: String) {
    override fun toString(): String {
        return address
    }

    fun country(): String {
        return "Korea"
    }
}

class Car(val name: String, val speed: Int) {
    init {
        println("객체가 초기화될 때 호출되는 블록입니다.")
    }

    fun go(address: Address) {
        // JS: String Template
        // println("Car go - $name($speed km) - $address")
        println("Car go - $name($speed km) - ${address.country()} ")
    }
}

fun main() {
    val car = Car("Hyundae", 120)
    car.go(Address("Suwon"))
}
*/

class Car {
    fun go() {
        println("Car go")
    }

    fun go(speed: Int) {
        println("Car go - $speed")
    }

    fun go(speed: Int, color: Int) {
        println("Car go - $speed / $color")
    }
}

fun main() {
    val car = Car()
    val a = 42           // speed
    val b = 100          // color

    // 인자가 어떤 파라미터로 전달되는지 코드를 통해 확인하기 어렵다.
    car.go(a)
    car.go(a, b)

    // 파라미터를 지정해서 호출하는 문법 - 가독성!
    car.go(speed = a)
    car.go(speed = a, color = b)

    // 순서를 바꾸는 것도 가능합니다. - 권장 되지 않음.
    car.go(color = 42, speed = 10)
}































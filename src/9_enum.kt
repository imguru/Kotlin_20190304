package ex9

// 1. enum
//   enum: 자바와 거의 비슷한 기능을 제공합니다.
//   Soft 키워드: class와 같이 사용될 때 의미가 있습니다.
/*
enum class Color {
    RED, ORANGE, YELLOW, GREEN, INDIGO
}
*/

// 2. enum은 객체입니다.
//    프로퍼티와 메소드를 가질 수 있습니다.
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    INDIGO(75, 0, 130);

    // 메소드를 정의하면 됩니다.
    // 반드시 ; 를 통해 영역을 구분해야 합니다.
    fun rgb(): Int {
        return (r * 256 + g) * 256 + b
    }
}

fun printName(color: Color) {
    val name = when (color) {
        Color.RED -> "Red"
        Color.ORANGE -> "Orange"
        Color.YELLOW -> "Yellow"
        Color.GREEN -> "Green"
        Color.INDIGO -> "Indigo"
    }

    println(name)
}

// switch - fallthrough
fun getWarmth(color: Color): String {
    return when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "Warm"
        Color.GREEN -> "Neutral"
        Color.INDIGO -> "Cold"
    }
}

// when 식의 인자로 상수 뿐 아니라, 임의의 객체도 가능하다.
fun mix(c1: Color, c2: Color): Color {
    val set = setOf(c1, c2)
    return when (set) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        else -> throw Exception("Dirty")
    }
}

fun main() {
    println(Color.RED.r)
    println(Color.RED.g)
    println(Color.RED.b)

    println(Color.INDIGO.rgb())

    printName(Color.GREEN)
    println(getWarmth(Color.GREEN))

    val age = 10


    // if 식입니다.
    val name = if (age > 30) {
        "Old Man"
    } else {
        "Young Man"
    }

    /*
    var name = ""
    if (age > 30)
        name = "Old man"
    else
        name = "Young man"
    */

}








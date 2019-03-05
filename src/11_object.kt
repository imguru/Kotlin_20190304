import java.io.File

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

/*
class CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}
*/

// - 상속 또는 인터페이스를 구현하는 것도 가능합니다.
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

data class Person(val name: String, val level: Int) {
    object NameComparator: Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int {
            return o1.name.compareTo(o2.name)
        }
    }

    object LevelComparator: Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int {
            return o1.level.compareTo(o2.level)
        }
    }
}

fun main(args: Array<String>) {
    val people = listOf(Person("Bob", 15),
        Person("Tom", 3))

    println(people.sortedWith(Person.NameComparator))
    println(people.sortedWith(Person.LevelComparator))



    val files = listOf(File("/Z"), File("/a"))

    // val sorted = files.sortedWith(CaseInsensitiveFileComparator())
    // println(sorted)

     val sorted = files.sortedWith(CaseInsensitiveFileComparator)
     println(sorted)


    Cursor.move(10, 20)
}



















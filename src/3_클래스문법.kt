import java.util.*

// 3_클래스 문법

// 1. 파일 하나에 여러개의 public 클래스를 제공할 수 있다.
// 2. 클래스의 기본 접근 지정은 public 입니다.
// 3. 생성자 - constructor
// 4. 초기화 블록 - init
/*
// User.java
public class User {
    private String name;
    private int age;
    public User(String name, int age) {
        this.name = name
        this.age = age
    }

    @Override
    boolean equals(Object other) {
        if (other == null)
            return false;

        if (other == this)
            return true;

        // other가 User 타입인지 먼저 체크해야 한다.
        // => Reflection
        // if (u.getClass() != User.class) // 1
        // if (u instanceOf User)          // 2
            return false

        User u = (User)other;

        return u.age == age &&
         Objects.equals(u.name, name);
    }
}
*/

// public class User
/*
// Version 1
class User {
    private val name: String
    private val age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
*/

/*
// Version 1
class User(name: String, age: Int) {
    private val name: String
    private val age: Int

    // 초기화 블록
    //  => 객체가 생성된 시점에 반드시 호출되는 블록
    init {
        this.name = name
        this.age = age
    }
}
*/

// ?
// => Nullable Type
//  : 일반적인 타입은 null을 담을 수 없습니다.
//    null을 담기 위해서는 타입의 뒤에 ?를 붙여야 합니다.
//  => 접근 해서 사용하기 전에 반드시 null check가 강제됩니다.
//  => Java의 @Nullable @NotNull을 통해 코틀린의 타입이 결정된다.

// Object -> Any
// * 객체의 equals를 오버라이드 하면 반드시 hashCode도 오버라이드 해야 한다.

// Data Object / Value Object
// => 반드시 hashCode, equals 재정의 해야 한다.
data class User(
    private val name: String,
    private val age: Int
)

/*
class User(
    private val name: String,
    private val age: Int
) {
    override fun hashCode(): Int {
        // Java 7: Objects.hash
        return Objects.hash(name, age)
    }

    override fun equals(other: Any?): Boolean {
        if (other === null) return false
        if (other === this) return true

//        if (other.javaClass !== javaClass)
//            return false
        if (other !is User)
            return false

        // Smart Cast
        // other as User
        return other.age == age &&
                other.name == name
    }
}
*/


fun main() {
    // User user = new User()
    // val user = User()

    // User user = new User("Tom", 42);

    // * Java - Guava 라이브러리
    // if (user1 == user2)      -> 참조 동등성
    // if (user1.equals(user2)) -> 객체 동등성
    //  =>  if (Objects.equals(user1, user2))

    val user1 = User("Tom", 42) // User("Tom", 42)
    val user2 = User("Tom", 42)

    if (user1 === user2) {
        println("Same object")
    } else {
        println("Diff object")
    }

    if (user1 == user2) {
        println("Same")
    } else {
        println("Diff")
    }

    // * Kotlin
    // 참조 동등성: ===
    // 객체 동등성: ==

}















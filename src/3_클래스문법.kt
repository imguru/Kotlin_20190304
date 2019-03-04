// 3_클래스 문법

// 1. 파일 하나에 여러개의 public 클래스를 제공할 수 있다.
// 2. 클래스의 기본 접근 지정은 public 입니다.

/*
// User.java
public class User {
    private String name;
    private int age;
    public User(String name, int age) {
        this.name = name
        this.age = age
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

class User(private val name: String,
           private val age: Int)

fun main() {
    // User user = new User()
    // val user = User()

    // User user = new User("Tom", 42);
    val user = User("Tom", 42)
}


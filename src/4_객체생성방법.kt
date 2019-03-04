package kex4

// 자바의 패키지
//  => 이름 충돌을 해결하기 위해서
//     라이브러러리의 카테고리화
//   제약: 디렉토리의 구조와 동일해야 한다.

//  코틀린의 패키지는 파일의 상단부에 어떤 제약없이 사용할 수 있다.
//   자바의 제약을 따르는 것을 권장한다.

// 각 인자의 기본값을 지정할 수 있는 기능이 존재합니다.
// 아래의 User는 자바에서는 사용할 수 없는 코드입니다..
/*
class User(
    private val name: String,
    private val address: String,
    private val age: Int = 1,
    private val level: Int = 1,
    private val money: Int = 0
)
*/

// 코틀린에서는 빌더를 더 이상 사용하지 않습니다.
class User @JvmOverloads constructor(
    private val name: String,
    private val address: String,
    private val age: Int = 1,
    private val level: Int = 1,
    private val money: Int = 0
)

fun main() {
    val user = User(name = "Tom",
        address = "Suwon", age = 40)
}















// 26_코틀린표준라이브러리.kt
package ex26

// 유입(신규 설치) / 전환(유료 결제)
// 유입 -> 앱 하나 설치 평균 비용(2000 ~ 4000)
// 전환 -> 유료 결제(리텐션 중요)

import java.io.File
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

// 그 외 유용한 기능을 살펴봅시다.
// 1. 조건 확인 함수: 예외 처리 구문을 편리하게 사용할 수 있도록 해준다.
//   : assert와 사용 방법이 유사하다.

// check   - IllegalStateException
// require - IllegalArgumentException

// 이미 존재하지 않는 파일에 메세지를 기록하는 함수
fun logMessage(filename: String, message: String) {
    val file = File(filename)

    if (file.exists()) {
        throw IllegalStateException()
    }

    // 위의 구문을 좀 더 편리하게 사용할 수 있는 함수 - check
    check(file.exists())

    // message 인자는 빈문자도 아니어야 한다.
    if (message.isBlank()) {
        throw IllegalArgumentException()
    }

    // 위의 구문을 좀 더 편리하게 사용할 수 있는 함수 - require
    require(message.isBlank())
}

fun main() {

}
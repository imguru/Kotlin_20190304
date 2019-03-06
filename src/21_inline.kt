import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

// 21_inline.kt

/*
class IncThread: Thread() {
    companion object {
        var n = 0
    }

    override fun run() {
        // for (i = 1; i <= 1000000; i++) - Java
        for (i in 1..1000000) {
            ++n
        }
    }
}

fun main() {
    val t1 = IncThread()
    val t2 = IncThread()

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println("n: ${IncThread.n}")
    // println(t1.n)
    // println(t2.n)
}
*/

// fun add(a: Int, b: Int) = a + b

// T: 코틀린의 {}은 표현식입니다. 결과가 존재합니다.
//  inline
//    : 모든 함수에 적용할 수 없습니다.
//      - 함수를 인자로 받는 형태의 함수에서 허용하는 기술입니다.
inline fun <T> withLock(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

class IncThread(private val lock: Lock) : Thread() {
    companion object {
        var n = 0
    }

    // 아래 코드의 문제점
    //  : lock / unlock을 아래 처럼 사용하면 CS 안에서 예외가 발생할 경우, unlock의
    //    호출이 보장되지 않습니다.
    //   => 예외 안정성
    /*
    override fun run() {
        for (i in 1..1000000) {
            lock.lock()
            ++n
            lock.unlock()
        }
    }
    */

    /*
    override fun run() {
        for (i in 1..1000000) {
            lock.lock()
            try {
                ++n
            } finally {
                lock.unlock()
            }
        }
    }
    */

    // 아래 코드의 문제점
    //  : withLock의 호출이 엄청나게 많으므로,
    //    함수를 사용하지 않을 때에 비해 성능 저하가 발생할 수 있다.
    //    => withLock 함수를 호출하는 것이 아니라, 컴파일 타임에 코드를 붙여넣는 기술
    //      : inline 함수
    override fun run() {
        for (i in 1..1000000) {
//            withLock(lock) {
//                ++n
//            }

            // Kotlin 라이브러리 형태 - Lock 인터페이스의 확장 함수
            lock.withLock {
                ++n
            }
        }
    }
}

fun main() {
    val lock = ReentrantLock()

    val t1 = IncThread(lock)
    val t2 = IncThread(lock)

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    println("n: ${IncThread.n}")
    // println(t1.n)
    // println(t2.n)
}
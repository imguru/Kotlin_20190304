package ex14_2

// 1. 메소드(함수) 호출 바인드
//   - 동적 바인딩: 객체의 타입에 의해서 호출되는 메소드가 결정되는 것
//     => 런타임

//   - 정적 바인딩: 객체를 참조하는 참조 변수의 타입에 의해서 호출되는 메소드가 결정되는 것
//     => 컴파일 타임

// final void foo() {} // 정적 바인딩
// fun foo()

open class View {
    // 2. 기존에 함수가 존재할 경우, 기존 함수가 우선된다.
    fun showOff() = println("I'm real View")
}

class Button : View() {
}
//-----------------------------------
// 3. 확장 함수 - 정적 바인딩을 하기 때문에, 참조 타입에 타입에 의해서
//              호출이 결정된다.
// fun View.showOff() = println("I'm View")

// 4. *확인 필요*
//    부모 타입이 동일한 이름의 메소드를 가지고 있다면, 사용할 수 없다.
fun Button.showOff() = println("I'm Button")

fun main() {
    // val v: View = Button()
    val v: Button = Button()
    v.showOff()
}




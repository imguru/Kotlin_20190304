// 1_시작
// 1) 코틀린 언어 특징
//  - 간결함
//    : 보일러플레이트를 제거합니다.
//  - 안전함
//    : Null 포인터를 안전하게 다루는 기능을 제공합니다.
//  - 상호 운용성
//    : Java로 작성된 코드를 아무 설정없이 사용할 수 있다.
//      Kotlin으로 작성된 코드를 자바에서 아무 설정없이 사용할 수 있다.

// 2) 컴파일 과정
//  $ kotlinc Hello.kt
//  $ kotlin HelloKt

//  $ java HelloKt

/*
// $ javap -c HelloKt
Compiled from "Hello.kt"
public final class HelloKt {
  public static final void main(java.lang.String[]);
    Code:
       0: aload_0
       1: ldc           #9                  // String args
       3: invokestatic  #15                 // Method kotlin/jvm/internal/Intrinsics.checkParameterIsNotNull:(Ljava/lang/Object;Ljava/lang/String;)V
       6: ldc           #17                 // String Hello, Kotlin
       8: astore_1
       9: getstatic     #23                 // Field java/lang/System.out:Ljava/io/PrintStream;
      12: aload_1
      13: invokevirtual #29                 // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
      16: return
}

$ kotlinc Hello.kt -include-runtime -d Hello.jar
$ java -jar Hello.jar

*/

// 3) REPL(Read-Eval-Print-Loop): kotlinc-jvm
//  : kotlinc-jvm -cp joda-time-2.10.1.jar
//   -> 외부 라이브러리도 로드해서 사용할 수 있다.

fun main() {
    println("Hello, Kotlin")
}

/*
fun main(args: Array<String>) {
    println("Hello, Kotlin")
}
*/

/*
// Sample.java
public class Sample {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
    }
}
*/
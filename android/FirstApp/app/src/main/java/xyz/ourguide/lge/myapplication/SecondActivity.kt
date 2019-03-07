package xyz.ourguide.lge.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.Appcompat

// Anko: Jetbrains
//   - Android Kotlin
//   - DSL(Domain Specific Language)
//    : 각 도메인에 특화된 언어
//    1) Anko Commons

//    2) Anko Layout
//      : xml(layout) -> kt/java
//        xml로 작성된 레이아웃 파일이 앱이 구동하면,
//        결국 자바(코틀린)으로 변환되어서 로드되어야 합니다.

//    3) Anko SQLite
//    4) Anko Coroutines
//      : Kotlin 코루틴 기능이 정식으로 1.3 부터 포함되었습니다.

// trailing lambda, infix

fun Context.xtoast(message: CharSequence): Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
    show()
}


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        button.setOnClickListener {

            // 1. Toast
            // - Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
            //  : toast("hello")
            //  : longToast("hello")

            // 2. Dialog - AlertDialog.Builder
            /*
            val alert = AlertDialog.Builder(this).apply {
                setTitle("Dialog Title")
                setMessage("Dialog Message")
                setPositiveButton("OK") { _, which ->
                    toast("OK")
                }
            }.create()
            alert.show()
            */

            /*
            alert(title = "Dialog Title", message = "DialogMessage") {
                positiveButton("OK") {
                    toast("OK")
                }

                negativeButton("Nope") {
                    toast("Nope!")
                }
            }.show()
            */

            // AppCompat Dialog
            /*
            alert(Appcompat, title = "Dialog Title", message = "DialogMessage") {
                positiveButton("OK") {
                    toast("OK")
                }

                negativeButton("Nope") {
                    toast("Nope!")
                }
            }.show()
            */

            // Selector Dialog
            /*
            val cities = listOf("Seoul", "Suwon", "Busan")
            selector(title = "Select your city", items = cities) { _, index ->
                toast("Selected - ${cities[index]}")
            }
            */

            // 3. Progress Dialog - deprecated!!!
            //  : 앱을 만들 때 절대 로딩 다이얼로그를 통해 처리하면 안됩니다.


            // 4. Activity 전환
            // val intent = Intent(this, MainActivity::class.java)
            // startActivity(intent)

            // 아래의 함수는 다음 액티비티로 데이터를 넘기지 않을 경우 사용할 수 있습니다.
            // startActivity<MainActivity>()

            // startActivity<MainActivity>(
            //        "name2" to "Tom",
            //        "age" to 42,
            //        "address" to "Suwon"
            // )

            val intent = intentFor<MainActivity>(
                    "name" to "Tom",
                    "age" to 42,
                    "address" to "Suwon"
            )
            startActivity(intent)

        }

    }
}










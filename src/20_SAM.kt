// 20_SAM.kt
package ex20

// 무명 객체 vs 람다
//interface OnClickListener {
//    fun onClick()
//}

class Button {
    var onClickListener: OnClickListener? = null
    var onClick: (() -> Unit)? = null

    fun click() {
        onClickListener?.onClick()
        onClick?.invoke()
    }
}

class Intent(context: Activity, clazz: Class<Activity>)

class Activity {
    fun startActivity(intent: Intent) {}

    // override
    fun onCreate() {
        // Button button = findViewById(R.id.button);
        val button = Button()
        button.onClickListener = object: OnClickListener {
            override fun onClick() {
                // this: 무명 객체에게 바인드됩니다.
                val intent = Intent(this@Activity, Activity::class.java)
                startActivity(intent)
            }
        }

        button.onClick = {
            // 람다는 객체가 아니기 때문에, this를 바인드 하지 않습니다.
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }

        // Kotlin - 자바의 SAM(Single Abstract Method) 인터페이스에 대해서는 람다로 변환해준다.
        //    SAM -> Functional Interface
        //    : 자바의 함수형 인터페이스에 대해서는 코틀린에서 람다의 형식으로 사용할 수 있습니다.
        button.onClickListener = OnClickListener {
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }
    }
}












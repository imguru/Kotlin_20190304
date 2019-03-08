package xyz.ourguide.lge.databindingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import xyz.ourguide.lge.databindingapp.databinding.ActivityMainBinding


// 안드로이드 App 아키텍쳐
//  1) MVC
//     Model / View / Controller(Activity)

//  2) MVP
//     Model / View / Presenter

//  3) MVVM
//     Model / View / ViewModel
//    1) ViewModel 수명 관리
//       - ViewModel(Headless Fragment)
//       - 위의 기법을 안드로이드가 공식적으로 라이브러리를 통해 제공하고 있습니다.
//    2) ViewModel과 View간의 '데이터 바인딩'
//       - 안드로이드 프레임워크가 제공하는 기능을 사용하면 편리합니다.

//  4) MVI
//     Model / View / Intent

// android X - Jetpack
//   => Modern Android Development
//   build.gradle 참조
//  1) ViewModel
//     lifecycle
//  2) Data Binding

// 1) User의 데이터가 변경되었을 때, View에게 알려주어야 한다.
// 2) 안드로이드 라이프 사이클을 적용해서 사용해야 합니다.
/*
data class User(val name: String, val age: Int, var likes: Int) {
    fun onLike() {
        Log.e("BBB", "onLike")
        likes += 1
    }
}
*/

// 주의사항: 직접 생성하면 안됩니다.
class User() : ViewModel() {
    // var name: String = "Tom"
    // var age: Int = 42
    // var likes: Int = 0

    val name = ObservableField("Tom")
    val age = ObservableInt(42)
    val likes = ObservableInt(0)

    fun onLike() {
        Log.e("BBB", "onLike")
        // likes += 1
        likes.set(likes.get() + 1)
        if (likes.get() > 10) {
            name.set("Bob")
            age.set(30)
        }
    }
}


class MainActivity : AppCompatActivity() {

    // val user = User("Tom", 42, 0)
    val user: User by lazy {
        // 직접 생성하는 것이 아니라 ViewModelProviders.of를 통해 생성해야 합니다.
        ViewModelProviders.of(this).get(User::class.java)
        // activity / fragment
    }

    // android:onClick = "onLike"
    fun onLike(view: View) {
        Log.e("AAA", "onLike")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.user = user
    }
}

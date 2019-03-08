package xyz.ourguide.lge.databindingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


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
//  1) ViewModel
//     lifecycle
//  2) Data Binding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

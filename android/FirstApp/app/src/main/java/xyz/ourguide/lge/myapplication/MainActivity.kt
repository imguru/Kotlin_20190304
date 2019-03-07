package xyz.ourguide.lge.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import xyz.ourguide.lge.myapplication.databinding.MainActivityBinding

// Project  - build.gradle
//   Module - build.gradle
//   Module
//   ...

// <Button
//        android:id="@+id/"
//    1) button_hello / btn_hello
//    2) hello_button
//    3) helloButton

// 1. onCreate 안에서 'findViewById' 에 대한 반복적인 코드가 발생한다.
//   => 3rd party 라이브러리: ButterKnife
//   => 코틀린에서는 버터나이프를 사용할 필요가 없습니다.

//     - kotlin-android-extension
//      => xml에 부여된 id를 바로 접근할 수 있는 기능을 제공합니다.
//      => xml에 부여된 id의 이름 체계를 코틀린의 이름 규칙을 사용해야 한다.

//     - android X = Data binding
//      => xml의 형식도 변경사항이 있습니다.
//      => setContentView를 더 이상 사용하지 않습니다.


class MainActivity : AppCompatActivity() {
    /*
    // Data Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. 바인딩 객체 생성
        //  R.layout.main_activity  => MainActivityBinding
        val binding: MainActivityBinding
                = DataBindingUtil.setContentView(this, R.layout.main_activity)

        // 2. binding 객체 안에서 뷰의 참조를 얻을 수 있습니다.
        // @+id/hello_button -> helloButton
        binding.helloButton.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        hello_button.setOnClickListener {
//            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
//        }

        // Fragment를 적용한다.
        val fragment = MainFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_frame, fragment)
            .commit()
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_hello)
        button.setOnClickListener {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
        }
    }
    */
}

class MainFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // ...
    }
}













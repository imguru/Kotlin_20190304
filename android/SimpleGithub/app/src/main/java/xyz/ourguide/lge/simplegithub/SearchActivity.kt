package xyz.ourguide.lge.simplegithub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// EditText의 내용이 변경될 때마다, 검색 API를 호출하는 기능을
// Rx + RxDataBinding 이용해서 만들어봅시다.

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

    }
}
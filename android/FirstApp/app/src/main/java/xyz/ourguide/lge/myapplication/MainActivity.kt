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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_view.view.*
import xyz.ourguide.lge.myapplication.databinding.MainActivityBinding
import kotlin.properties.Delegates

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

    val adapter: CityAdapter = CityAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // view.findViewById(R.id.recycler_view) - KTX
        // recyclerView
        //   1) item_view.xml
        //   2) adapter

        //
        // 1. requireActivity(): IllegalStateException
        // 2. let: null이 아닌 경우 처리하도록 로직을 작성할 수 있다.
        activity?.let { activity ->
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter
        }

        reloadButton.setOnClickListener {
            adapter.countries = listOf(
                    Country("Korea", "KR"),
                    Country("Korea", "KR"),
                    Country("Korea", "KR")
            )
            // adapter의 데이터를 변경했을 경우, 데이터가 변경되었다는 사실을 adapter에게 알려야 한다.
            // adapter.notifyDataSetChanged()
        }
    }
}

data class Country(val name: String, val code: String)

class CityAdapter : RecyclerView.Adapter<CityAdapter.Holder>() {
    // var countries: List<Country> = emptyList()

    // 프로퍼티 위임 객체를 사용해서, 위의 프로퍼티의 값이 변경될 때마다
    // 자동으로 notifyDataSetChanged 수행될 수 있도록 한다.
    var countries: List<Country> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder
        = Holder(parent)

    override fun getItemCount(): Int = countries.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // holder.itemView.nameTextView.text = "China"
        // holder.itemView.codeTextView.text = "CN"
        val model = countries[position]

        with (holder.itemView) {
            nameTextView.text = model.name
            codeTextView.text = model.code
        }
    }

    class Holder(parent: ViewGroup)
        : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false))
}












